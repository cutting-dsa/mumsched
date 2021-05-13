package edu.mum.mumsched.students.service;

import edu.mum.mumsched.entries.entity.Entry;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.users.model.AppUser;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public interface StudentService {
    List<Student> getStudents();
    Student getStudent(Long id);
    Student getStudentByUserId(Long id);
    Student updateStudent(Long id, Student student);
    Student deleteStudent(Student student);
    Student save(Student student);
    BigInteger generateRegistrationNumber();
    Student createStudent(Entry entry, AppUser user, AppUser loggedUser);
    BigInteger newRegistrationNumber();
}
