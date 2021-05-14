package edu.mum.mumsched.sectionenrollment.service;

import edu.mum.mumsched.sectionenrollment.repository.SectionEnrollmentRepository;
import edu.mum.mumsched.students.repository.StudentRepository;
import edu.mum.mumsched.students.service.StudentServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
class SectionEnrollmentServiceImplTest {

    @InjectMocks
    SectionEnrollmentServiceImpl sectionEnrollmentService;

    @Mock
    SectionEnrollmentRepository repository;



}