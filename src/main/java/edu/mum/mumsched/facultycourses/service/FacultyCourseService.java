package edu.mum.mumsched.facultycourses.service;

import edu.mum.mumsched.courses.entity.Course;
import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.facultycourses.model.FacultyCourse;

import java.util.Collection;

public interface FacultyCourseService {
    void save(FacultyCourse facultyCourse);
    void edit(FacultyCourse facultyCourse);
    void delete(Long id);

    FacultyCourse getFacultyCourse(Long id);

    Collection<Course> getFacultyCourses(Long facultyId);

    Collection<FacultyCourse> getAllFacultyCourses();

    Collection<Faculty> getFacultyByCourseInBlock(Long blockId, Long courseId);


}
