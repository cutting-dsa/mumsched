package edu.mum.mumsched.courses.controller;

import edu.mum.mumsched.courses.entity.Course;
import edu.mum.mumsched.courses.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @RequestMapping(value ="/courses", method = RequestMethod.GET)
    public String getAllCourses(Model model){
        Collection<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses/view";
    }

    @RequestMapping(value ="/courses/create", method = RequestMethod.GET)
    public String getCourse(Model model) {
        model.addAttribute("newcourse", new Course());
        return "courses/create";
    }

    @RequestMapping(value ="/courses", method = RequestMethod.POST)
    public String createCourse(Model model, @RequestBody Course course){
       //Course newCourse = courseService.addCourse(course);
       //model.addAttribute("newCourse", newCourse);
       return  "redirect:/courses";
    }

    @RequestMapping(value ="/courses/{id}", method = RequestMethod.PUT)
    public String editCourse(@ModelAttribute("updateCourse") @Valid Course course,
                             BindingResult result, Model model, @PathVariable int id){
        if(result.hasErrors())
            return "courses/edit";

        courseService.updateCourse(id, course);
        return "redirect:/courses/";
    }

    @RequestMapping(value ="/courses/{id}", method = RequestMethod.DELETE)
    public String deleteCourse(@PathVariable int id,
                               BindingResult result, Model model){
        if(result.hasErrors())
            return "courses/view";

        courseService.deleteCourse(id);
        return  "redirect: /courses";
    }
}
