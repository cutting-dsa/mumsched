package edu.mum.mumsched.sectionenrollment.service;

import edu.mum.mumsched.sectionenrollment.repository.SectionEnrollmentRepository;
import edu.mum.mumsched.sections.model.Section;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SectionEnrollmentServiceImpl implements SectionEnrollmentService{

    @Autowired
    private SectionEnrollmentRepository repository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Section> getAllSections() {
        return repository.findAll();
    }

    @Override
    public List<Section> getSectionsByBlockId(Long id) {
        return repository.findSectionByBlock_Id(id);
    }

    @Override
    public Section getSectionById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public void enrollStudentSection(Section section, Student student) {
        student.getSections().addAll(Arrays.asList(section));
        if(student.getSections().size() == 4) {
            student.setHasRegisteredCourses(true);
        }
        studentRepository.save(student);
    }

    @Override
    public boolean studentAlreadyEnrolledInSection(Section section, Student student) {
        return student.getSections().contains(section);
    }
}
