package edu.mum.mumsched.students.repository;

import edu.mum.mumsched.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select max(s.registrationNumber) as registrationNumber from Student s")
    BigInteger getMostRecentRegistration();

    Student getStudentByUserId(Long id);
}
