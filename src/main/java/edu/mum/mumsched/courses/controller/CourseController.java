package edu.mum.mumsched.courses.controller;

import edu.mum.mumsched.courses.entity.Course;
import edu.mum.mumsched.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value ="/courses", method = RequestMethod.GET)
    public String getAllCourses(Model model){
        Collection<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses/view";
    }

    @RequestMapping(value ="/courses/create")
    public String getCourse(@ModelAttribute("newcourse") Course course, Model model) {
        return "courses/create";
    }

    @RequestMapping(value ="/courses/create", method = RequestMethod.POST)
    public String createCourse(@ModelAttribute("newcourse") @Valid Course course, BindingResult result, Model model){
        if (result.hasErrors())
            return "courses/create";

       courseService.save(course);
       return  "redirect:/courses";
    }

    @RequestMapping(value ="/courses/edit/{id}", method = RequestMethod.GET)
    public String editCourse(Model model, @PathVariable Long id){
        Course course = courseService.getCourse(id);
        model.addAttribute("updateCourse", course);
        return "/courses/edit";
    }
    @RequestMapping(value ="/courses/edit/{id}", method = RequestMethod.PUT)
    public String editCourse(@ModelAttribute("updateCourse") @Valid Course course,
                             BindingResult result, Model model, @PathVariable Long id){
        if(result.hasErrors())
            return "courses/edit";

        courseService.updateCourse(id, course);
        return "redirect:/courses/";
    }

    @RequestMapping(value ="/courses/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id, BindingResult result, Model model){
        if(result.hasErrors())
            return "courses/view";

        courseService.deleteCourse(id);
        return  "redirect: /courses";
    }
}
