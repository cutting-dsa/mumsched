package edu.mum.mumsched.sectionenrollment.controller;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.blocks.service.BlockService;
import edu.mum.mumsched.sectionenrollment.domain.SectionEnrollmentParameters;
import edu.mum.mumsched.sectionenrollment.service.SectionEnrollmentService;
import edu.mum.mumsched.sections.model.Section;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
public class SectionEnrollmentController {
    public static final Logger logger = LogManager.getLogger(SectionEnrollmentController.class);

    @Autowired
    private SectionEnrollmentService sectionEnrollmentService;

    @Autowired
    private BlockService blockService;

    @Autowired
    private StudentService studentService;

    @RequestMapping("/sections")
    public String index(Model model) {
        model.addAttribute("sections", sectionEnrollmentService.getAllSections());
        return "enrollment/sections";
    }

    @RequestMapping("/enrollments")
    public String enrollments(Model model) {
        Student student = studentService.getStudent(1L);
        List<Block> blocks = blockService.getBlocksByEntry(student.getEntry());
        model.addAttribute("blocks", blocks);

        /*
        1. get student entry
        2. pass the entry dates and use to filter when fetching the blocks
         */


        return "enrollment/enrollments";
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @RequestMapping("/block-sections/{blockId}")
    public String sectionCourses(@PathVariable("blockId") Long blockId,
                                 Model model) {
        List<Section> sections = sectionEnrollmentService.getSectionsByBlockId(blockId);
        model.addAttribute("sections", sections);

        /*
        1.get sections already enrolled by student so that we can display as already enrolled
        in a specific block
         */
        Student student = studentService.getStudent(1L);
        Collection<Section> sectionEnrollmentsPerBlock = sectionEnrollmentService.getSectionsByStudentPerBlock(student, blockId);

        model.addAttribute("enrolledSections", sectionEnrollmentsPerBlock);

        return "enrollment/sectioncourses";
    }

    @PostMapping(value = "/enroll-section", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    HttpStatus enrollSection(@RequestBody SectionEnrollmentParameters parameters) {
        try {
            Section section = sectionEnrollmentService.getSectionById(parameters.getSectionId());
            if (section == null) {
//                return new ResponseEntity<String>("Sorry,an error occured. Please try again", HttpStatus.BAD_REQUEST);
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            //should be from authenticated user
            Student student = studentService.getStudent(1L);
            if (student == null) {
//                return new ResponseEntity<String>("Sorry,an error occured. Please try again", HttpStatus.BAD_REQUEST);
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            //check if student had already enrolled to this course
            if (sectionEnrollmentService.studentAlreadyEnrolledInSection(section, student)) {
//                return new ResponseEntity<String>("Sorry,you have already enrolled to this section", HttpStatus.BAD_REQUEST);
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            sectionEnrollmentService.enrollStudentSection(section, student);
            return HttpStatus.OK;
        } catch (EmptyResultDataAccessException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @PostMapping(value = "/unenroll-section", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    HttpStatus unEnrollSection(@RequestBody SectionEnrollmentParameters parameters) {

        Section section = sectionEnrollmentService.getSectionById(parameters.getSectionId());
        Student student = studentService.getStudent(1L);

        if (!student.getSections().contains(section)) {
            // student doesnt have the section already
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            sectionEnrollmentService.unEnrollStudentSection(section, student);
            return HttpStatus.OK;
        }
    }

}
