package edu.mum.mumsched.sectionenrollment.service;

import edu.mum.mumsched.sections.model.Section;
import edu.mum.mumsched.students.model.Student;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public interface SectionEnrollmentService {
    List<Section> getAllSections();
    List<Section> getSectionsByBlockId(Long id);
    Section getSectionById(Long id);
    void enrollStudentSection(Section section, Student student);
    boolean studentAlreadyEnrolledInSection(Section section, Student student);
    Collection<Section> getSectionsByStudent(Student student);
}
