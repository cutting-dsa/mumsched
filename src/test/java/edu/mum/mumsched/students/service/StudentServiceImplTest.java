package edu.mum.mumsched.students.service;

import edu.mum.mumsched.students.Factory;
import edu.mum.mumsched.students.model.AtomicBigInteger;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
class StudentServiceImplTest {
    @Spy
    @InjectMocks
    StudentServiceImpl studentService;

    @Mock
    StudentRepository studentRepository;

    @Test
    public void createStudentSuccessfully() throws Exception {
        Student student = Factory.createStudent();
        when(studentRepository.save(student)).thenReturn(student);
        studentService.save(student);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testGenerateIncrementedRegistrationNumber() {
        BigInteger initialRegNumber = new BigInteger("1");
        AtomicBigInteger atomicBigInteger = new AtomicBigInteger(initialRegNumber);
        BigInteger newRegistrationNumber = atomicBigInteger.incrementAndGet();
        Assertions.assertTrue(newRegistrationNumber.subtract(initialRegNumber).equals(new BigInteger("1")));
    }

    @Test
    public void testStudentDeletion() {
        Student student = Factory.createStudent();
        when(studentService.deleteStudent(any(Student.class))).thenReturn(student);
        Student deletedStudent = studentService.deleteStudent(student);
        Assertions.assertFalse(deletedStudent.getActive());
    }

}