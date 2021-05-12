package edu.mum.mumsched.sections.service;

import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.sections.model.Section;

import java.util.Collection;

public interface SectionService {

    void save(Section section);
    void edit(Section section);
    void delete(Long id);
    Section getSection(Long id);
    Collection<Section> getAllSections();
}
