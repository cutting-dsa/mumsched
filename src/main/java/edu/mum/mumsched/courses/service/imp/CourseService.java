package edu.mum.mumsched.courses.service.imp;

import edu.mum.mumsched.courses.domain.Course;
import edu.mum.mumsched.courses.repository.ICourseRepository;
import edu.mum.mumsched.courses.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements ICourseService {
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
