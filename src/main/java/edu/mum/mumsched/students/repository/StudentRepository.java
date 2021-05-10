package edu.mum.mumsched.students.repository;

import edu.mum.mumsched.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
