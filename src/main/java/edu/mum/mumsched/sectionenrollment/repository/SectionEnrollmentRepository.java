package edu.mum.mumsched.sectionenrollment.repository;

import edu.mum.mumsched.sectionenrollment.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface SectionEnrollmentRepository extends JpaRepository<Section, Long> {
    List<Section> findSectionByBlock_Id(Long id);
}
