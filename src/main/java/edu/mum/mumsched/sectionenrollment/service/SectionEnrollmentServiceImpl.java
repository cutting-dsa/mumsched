package edu.mum.mumsched.sectionenrollment.service;

import edu.mum.mumsched.sectionenrollment.domain.Section;
import edu.mum.mumsched.sectionenrollment.repository.SectionEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SectionEnrollmentServiceImpl implements SectionEnrollmentService{

    @Autowired
    private SectionEnrollmentRepository repository;

    @Override
    public List<Section> getAllSections() {
        return repository.findAll();
    }
}
