package edu.mum.mumsched.facultycourses.controller;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.blocks.service.BlockService;
import edu.mum.mumsched.courses.entity.Course;
import edu.mum.mumsched.courses.service.CourseService;
import edu.mum.mumsched.facultycourses.model.FacultyCourse;
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
@RequestMapping("/faculties/courses")
public class FacultyCourseController {

    @Autowired
    BlockService blockService;

    @Autowired
    FacultyCourseService facultyCourseService;

    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getFacultyCourses(Model model) {
        Collection<FacultyCourse> facultyCourses = facultyCourseService.getAllFacultyCourses();
        model.addAttribute("facultyCourses", facultyCourses);
        return "faculties/courses/view";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {

        Collection<Block> blocks = blockService.getAllBlocks();
        Collection<Course> courses = courseService.getAllCourses();

        model.addAttribute("facultyCourse", new FacultyCourse());
        model.addAttribute("blocks", blocks);
        model.addAttribute("courses", courses);

        return "faculties/courses/create";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") Long id) {

        FacultyCourse facultyCourse = facultyCourseService.getFacultyCourse(id);

        Collection<Block> blocks = blockService.getAllBlocks();
        Collection<Course> courses = courseService.getAllCourses();

        model.addAttribute("facultyCourse", facultyCourse);
        model.addAttribute("blocks", blocks);
        model.addAttribute("courses", courses);
        return "faculties/courses/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("faculty") @Valid FacultyCourse facultyCourse,
                       BindingResult result,
                       Model model) {

        facultyCourseService.save(facultyCourse);

        return "redirect:/faculties/courses/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, params = "submit")
    public String update(@ModelAttribute("faculty") @Valid FacultyCourse facultyCourse,
                         BindingResult result,
                         Model model, @PathVariable("id") Long id) {

        if(result.hasErrors()) {
            return "faculties/courses/edit";
        }
        facultyCourse.setId(id);
        facultyCourseService.edit(facultyCourse);

        return "redirect:/faculties/courses/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, params = "cancel")
    public String cancel(@ModelAttribute("faculty") @Valid FacultyCourse facultyCourse,
                         BindingResult result, Model model) {
        model.addAttribute("message", "You clicked cancel, please re-enter employee details:");
        //return "faculties/courses/view";
        return "redirect:/faculties/courses/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {

        facultyCourseService.delete(id);
    }
}
