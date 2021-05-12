package edu.mum.mumsched.courses.service.impl;

import edu.mum.mumsched.courses.entity.Course;
import edu.mum.mumsched.courses.repository.CourseRepository;
import edu.mum.mumsched.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        courseRepository.findAll().forEach(courseList :: add);
        return courseList;
    }

    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Long id, Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
