package edu.mum.mumsched.faculty.service;

import edu.mum.mumsched.faculty.model.Faculty;

import java.util.Collection;

public interface FacultyService {

    Long save(Faculty faculty);
    Long edit(Faculty faculty);
    void delete(Long id);
    Faculty getFaculty(Long id);
    Collection<Faculty> getAllFaculties();

    Faculty getFacultyByUserId(Long id);
}
