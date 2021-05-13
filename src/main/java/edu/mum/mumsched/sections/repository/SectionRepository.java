package edu.mum.mumsched.sections.repository;

import edu.mum.mumsched.sections.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
