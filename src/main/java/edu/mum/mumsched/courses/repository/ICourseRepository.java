package edu.mum.mumsched.courses.repository;

import edu.mum.mumsched.courses.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends CrudRepository<Course, Integer> {
}
