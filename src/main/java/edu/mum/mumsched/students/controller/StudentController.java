package edu.mum.mumsched.students.controller;

import edu.mum.mumsched.students.model.DeleteStudentParameters;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.service.StudentService;
import edu.mum.mumsched.users.model.AppUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Controller
public class StudentController {

    public static final Logger LOGGER = LogManager.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "students/all";
    }

    @GetMapping("/students/{id}")
    public String getStudent(@PathVariable @Min(0) Long id, Model model) {
        model.addAttribute("student", studentService.getStudent(id));
        return "students/view";
    }

    @GetMapping(value = "/students/add")
    public String studentRegForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "students/create";
    }

    @PostMapping("/students")
    public String processStudent(@Valid Student student,
                                 Errors errors,
                                 SessionStatus sessionStatus,
                                 @AuthenticationPrincipal AppUser user) {
        if (errors.hasErrors()) {
            return "students/create";
        }

        student.setUser(user);
        studentService.save(student);
        sessionStatus.setComplete();

        return "redirect:/students/all";
    }

    @GetMapping("/students/edit/{id}")
    @Validated
    public String showUpdateForm(@PathVariable @Min(0) Long id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        LOGGER.info("fetch finished " + model);
        return "students/view";
    }

    @PatchMapping(path = "/students/{studentId}")
    public String patchStudent(@PathVariable("studentId") @Min(0) Long studentId,
                               @RequestBody Student student, Model model) {
        model.addAttribute("student", studentService.updateStudent(studentId, student));
        return "students/view";
    }

    @DeleteMapping(value = "/students/delete", consumes = "application/json", produces = "application/json")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteStudent(@RequestBody DeleteStudentParameters parameters) {
        try {
            Student student = studentService.getStudent(parameters.getStudentId());
             studentService.deleteStudent(student);
        } catch (EmptyResultDataAccessException e) {

        }
    }
}
