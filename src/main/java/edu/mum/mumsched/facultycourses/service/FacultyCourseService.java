package edu.mum.mumsched.facultycourses.service;

import edu.mum.mumsched.facultycourses.model.FacultyCourse;
import edu.mum.mumsched.sections.model.Section;

import java.util.Collection;

public interface FacultyCourseService {
    void save(FacultyCourse facultyCourse);
    void edit(FacultyCourse facultyCourse);
    void delete(Long id);
    FacultyCourse getFacultyCourse(Long id);
    Collection<FacultyCourse> getAllFacultyCourses();
}
