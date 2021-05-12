package edu.mum.mumsched.sectionenrollment.controller;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.blocks.service.BlockService;
import edu.mum.mumsched.sectionenrollment.domain.SectionEnrollmentParameters;
import edu.mum.mumsched.sectionenrollment.service.SectionEnrollmentService;
import edu.mum.mumsched.sections.model.Section;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.service.StudentService;
import edu.mum.mumsched.users.model.AppUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
        List<Block> blocks = blockService.getAllBlocks();
        model.addAttribute("blocks", blocks);
        return "enrollment/enrollments";
    }

    @RequestMapping("/block-sections/{blockId}")
    public String sectionCourses(@PathVariable("blockId") Long blockId,
                                 Model model,
                                 Principal principal) {
        List<Section> sections = sectionEnrollmentService.getSectionsByBlockId(blockId);
        model.addAttribute("sections", sections);
        logger.info("****************");
        logger.info(principal.getName());
        return "enrollment/sectioncourses";
    }

    @PostMapping(value = "/enroll-section", consumes = "application/json", produces = "application/json")
    public @ResponseBody HttpStatus enrollSection(@RequestBody SectionEnrollmentParameters parameters) {
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

}
