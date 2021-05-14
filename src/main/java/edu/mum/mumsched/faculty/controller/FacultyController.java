package edu.mum.mumsched.faculty.controller;

import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.faculty.service.FacultyService;
import edu.mum.mumsched.facultycourses.service.FacultyCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

    @Autowired
    FacultyService facultyService;

    @Autowired
    FacultyCourseService facultyCourseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getFaculties(Model model) {
        Collection<Faculty> faculties = facultyService.getAllFaculties();
        model.addAttribute("faculties", faculties);
        return "faculties/view";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "faculties/create";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") Long id) {
        Faculty faculty = facultyService.getFaculty(id);

        faculty.setFirstName(faculty.getUser().getFirstName());
        faculty.setLastName(faculty.getUser().getLastName());
        faculty.setEmail(faculty.getUser().getEmail());

        model.addAttribute("faculty", faculty);
        return "faculties/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("faculty") @Valid Faculty faculty,
                       BindingResult result,
                       Model model) {

        facultyService.save(faculty);

        return "redirect:/faculties/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("faculty") @Valid Faculty faculty,
                         BindingResult result,
                         Model model, @PathVariable("id") Long id) {

        if(result.hasErrors()) {
            return "faculties/edit";
        }
        faculty.setId(id);
        facultyService.edit(faculty);

        return "redirect:/faculties/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {

        facultyService.delete(id);
    }

    @RequestMapping(value = "/course/{courseId}/block/{blockId}")
    public Collection<Faculty> getFacultyTeachingCourseInBlock(@PathVariable("blockId") Long blockId,
                                                               @PathVariable("courseId") Long courseId){

        return facultyCourseService.getFacultyByCourseInBlock(blockId, courseId);
    }

}
