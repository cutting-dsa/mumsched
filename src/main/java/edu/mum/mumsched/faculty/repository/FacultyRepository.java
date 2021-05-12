package edu.mum.mumsched.faculty.repository;

import edu.mum.mumsched.faculty.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
