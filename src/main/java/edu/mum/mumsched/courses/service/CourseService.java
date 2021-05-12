package edu.mum.mumsched.courses.service;

import edu.mum.mumsched.courses.entity.Course;

import java.util.Collection;

public interface CourseService {
    Collection<Course> getAllCourses();

    void save(Course course);

    void updateCourse(Long id, Course course);

    void deleteCourse(Long id);

    Course getCourse(Long id);
}
