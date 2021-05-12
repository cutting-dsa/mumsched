package edu.mum.mumsched.courses.service;

import edu.mum.mumsched.courses.entity.Course;
import edu.mum.mumsched.courses.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private ICourseRepository iCourseRepository;

    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        iCourseRepository.findAll().forEach(courseList :: add);
        return courseList;
    }

    public Course getCourse(int id) {
        return iCourseRepository.findById(id).orElse(null);
    }

    public void addCourse(Course course) {
        iCourseRepository.save(course);
    }

    public void updateCourse(int id, Course course) {
        iCourseRepository.save(course);
    }

    public void deleteCourse(int id) {
        iCourseRepository.deleteById(id);
    }
}
