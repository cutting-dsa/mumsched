package edu.mum.mumsched.sections.controller;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.blocks.service.BlockService;
import edu.mum.mumsched.courses.entity.Course;
import edu.mum.mumsched.courses.service.CourseService;
import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.faculty.service.FacultyService;
import edu.mum.mumsched.sections.model.Section;
import edu.mum.mumsched.sections.service.SectionService;
import edu.mum.mumsched.students.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private BlockService blockService;

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSections(Model model) {
        Collection<Section> sections = sectionService.getAllSections();
        model.addAttribute("sections", sections);
        return "sections/view";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {

        Collection<Faculty> faculties = facultyService.getAllFaculties();

        Collection<Block> blocks = blockService.getAllBlocks();
        Collection<Course> courses = courseService.getAllCourses();

        model.addAttribute("section", new Section());
        model.addAttribute("blocks", blocks);
        model.addAttribute("faculties", faculties);
        model.addAttribute("courses", courses);

        return "sections/create";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") Long id) {
        Section section = sectionService.getSection(id);

        Collection<Faculty> faculties = facultyService.getAllFaculties();
        Collection<Block> blocks = blockService.getAllBlocks();
        Collection<Course> courses = courseService.getAllCourses();

        model.addAttribute("section", section);
        model.addAttribute("blocks", blocks);
        model.addAttribute("faculties", faculties);
        model.addAttribute("courses", courses);
        return "sections/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("section") @Valid Section section,
                       BindingResult result,
                       Model model) throws Exception {

        try {
            sectionService.save(section);
            return "redirect:/sections/";
        } catch (Exception e){
            model.addAttribute("errorMessage",e.getMessage());
            model.addAttribute("section",section);

            Collection<Faculty> faculties = facultyService.getAllFaculties();
            Collection<Block> blocks = blockService.getAllBlocks();
            Collection<Course> courses = courseService.getAllCourses();

            model.addAttribute("blocks", blocks);
            model.addAttribute("faculties", faculties);
            model.addAttribute("courses", courses);

            return "/sections/create";
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("section") @Valid Section section,
                         BindingResult result,
                         Model model, @PathVariable("id") Long id) {

        if(result.hasErrors()) {
            return "sections/edit";
        }
        section.setId(id);
        sectionService.edit(section);

        return "redirect:/sections/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {

        sectionService.delete(id);
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView getException(Exception ex) {
        return new ModelAndView("sections/create", "error", ex.getMessage());
    }
    @RequestMapping(value = "/members/{id}", method = RequestMethod.GET)
    public String getSectionMembers(Model model, @PathVariable("id") Long id) {
        List<Student> studentList = sectionService.getSectionMembers(id);
        model.addAttribute("members", studentList);
        model.addAttribute("section", sectionService.getSection(id));
        return "sections/members";
    }

}
