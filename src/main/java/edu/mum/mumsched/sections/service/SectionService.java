package edu.mum.mumsched.sections.service;

import edu.mum.mumsched.sections.model.Section;
import edu.mum.mumsched.students.model.Student;

import java.util.Collection;
import java.util.List;

public interface SectionService {

    void save(Section section);
    void edit(Section section);
    void delete(Long id);
    Section getSection(Long id);
    Collection<Section> getAllSections();
    List<Student> getSectionMembers(Long id);
}
