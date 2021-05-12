package edu.mum.mumsched.courses.controller;

import edu.mum.mumsched.courses.entity.Course;
import edu.mum.mumsched.courses.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @RequestMapping(value ="/courses", method = RequestMethod.GET)
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @RequestMapping(value ="/courses/{id}", method = RequestMethod.GET)
    public Course getCourse(@PathVariable int id){
        return courseService.getCourse(id);
    }

    @RequestMapping(value ="/courses", method = RequestMethod.POST)
    public void createCourse(@RequestBody Course course){
        courseService.addCourse(course);
    }

    @RequestMapping(value ="/courses/{id}", method = RequestMethod.PUT)
    public void editCourse(@PathVariable int id, @RequestBody Course course){
        courseService.updateCourse(id, course);
    }

    @RequestMapping(value ="/courses/{id}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable int id){
        courseService.deleteCourse(id);
    }
}
