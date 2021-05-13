package edu.mum.mumsched.sectionenrollment.service;

import edu.mum.mumsched.sectionenrollment.controller.SectionEnrollmentController;
import edu.mum.mumsched.sectionenrollment.repository.SectionEnrollmentRepository;
import edu.mum.mumsched.sections.model.Section;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SectionEnrollmentServiceImpl implements SectionEnrollmentService{

    public static final Logger logger = LogManager.getLogger(SectionEnrollmentServiceImpl.class);

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

    @Override
    public Collection<Section> getSectionsByStudent(Student student) {
        return student.getSections();
    }

    @Override
    public void unEnrollStudentSection(Section section, Student student) {
        logger.info("***************************");
        logger.info(section);
        logger.info(student);
        student.getSections().remove(section);
        studentRepository.save(student);
        logger.info(student);
    }

    @Override
    public Collection<Section> getSectionsByStudentPerBlock(Student student, Long blockId) {
        return student
                .getSections()
                .stream()
                .filter(section -> section.getBlock()
                        .getId().equals(blockId))
                .collect(Collectors.toList());
    }
}
