package edu.mum.mumsched.sectionenrollment.service;

import edu.mum.mumsched.sectionenrollment.domain.Section;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public interface SectionEnrollmentService {
    List<Section> getAllSections();
}
