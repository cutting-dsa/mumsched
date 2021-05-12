package edu.mum.mumsched.faculty.service;

import edu.mum.mumsched.faculty.model.Faculty;

import java.util.Collection;

public interface FacultyService {

    void save(Faculty faculty);
    void edit(Faculty faculty);
    void delete(Long id);
    Faculty getFaculty(Long id);
    Collection<Faculty> getAllFaculties();
}
