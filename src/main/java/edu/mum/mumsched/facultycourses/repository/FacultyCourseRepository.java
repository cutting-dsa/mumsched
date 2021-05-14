package edu.mum.mumsched.facultycourses.repository;

import edu.mum.mumsched.facultycourses.model.FacultyCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FacultyCourseRepository extends JpaRepository<FacultyCourse, Long> {

    Collection<FacultyCourse> getFacultyCourseByFacultyId(Long id);

    Collection<FacultyCourse> getFacultyCoursesByBlockIdAndCourseId(Long blockId, Long courseId);

}
