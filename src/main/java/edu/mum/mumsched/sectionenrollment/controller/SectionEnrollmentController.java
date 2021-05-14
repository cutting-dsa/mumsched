package edu.mum.mumsched.sectionenrollment.controller;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.blocks.service.BlockService;
import edu.mum.mumsched.config.security.SecurityHelper;
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

    @PreAuthorize("hasAuthority('STUDENT')")
    @RequestMapping("/enrollments")
    public String enrollments(Model model) {
        AppUser loggedUser = SecurityHelper.getLoggedInUser();

        Student student = studentService.getStudentByUserId(loggedUser.getId());
        //TODO: fetch block my student track
        List<Block> blocks = blockService.getBlocksByEntry(student);
        model.addAttribute("blocks", blocks);
        return "enrollment/enrollments";
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @RequestMapping("/block-sections/{blockId}")
    public String sectionCourses(@PathVariable("blockId") Long blockId,
                                 Model model) {
        List<Section> sections = sectionEnrollmentService.getSectionsByBlockId(blockId);
        model.addAttribute("sections", sections);

        AppUser loggedUser = SecurityHelper.getLoggedInUser();
        Student student = studentService.getStudentByUserId(loggedUser.getId());
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
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            AppUser loggedUser = SecurityHelper.getLoggedInUser();
            Student student = studentService.getStudentByUserId(loggedUser.getId());

            if (student == null) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            //check if student had already enrolled to this course
            if (sectionEnrollmentService.studentAlreadyEnrolledInSection(section, student)) {
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
        AppUser loggedUser = SecurityHelper.getLoggedInUser();
        Student student = studentService.getStudentByUserId(loggedUser.getId());

        if (!student.getSections().contains(section)) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            sectionEnrollmentService.unEnrollStudentSection(section, student);
            return HttpStatus.OK;
        }
    }

    @PostMapping(value = "/unenroll-student-section/{studentId}/{sectionId}", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    HttpStatus unEnrollStudentSection(@PathVariable("studentId") Long studentId, @PathVariable("sectionId") Long sectionId) {

        Section section = sectionEnrollmentService.getSectionById(sectionId);
        Student student = studentService.getStudentByUserId(studentId);

        if (!student.getSections().contains(section)) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            sectionEnrollmentService.unEnrollStudentSection(section, student);
            return HttpStatus.OK;
        }
    }

}
