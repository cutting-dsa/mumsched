package edu.mum.mumsched.students.controller;

import edu.mum.mumsched.students.model.AddUserForm;
import edu.mum.mumsched.students.model.DeleteStudentParameters;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.service.StudentService;
import edu.mum.mumsched.users.model.AppUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigInteger;

@Controller
public class StudentController {

    public static final Logger LOGGER = LogManager.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

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
    public String processStudent(@Valid AddUserForm student,
                                 Errors errors,
                                 SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "students/create";
        }

        BigInteger recentRegNo = studentService.generateRegistrationNumber();

        //save user
//        AppUser newUser = new AppUser(1L,student.getEmail(), student.getFirstName(), student.getLastName(), passwordEncoder.encode("secret"), 1);
//        AppUser user = userDetailsService.save(newUser);
//
//        Student newStudent = new Student();
//        newStudent.setUser(user);
//        newStudent.setRegistrationNumber(recentRegNo);
//
//        studentService.save(newStudent);
//        sessionStatus.setComplete();

        return "redirect:/students/all";
    }

    @GetMapping("/students/edit/{id}")
    @Validated
    public String showUpdateForm(@PathVariable @Min(0) Long id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "students/view";
    }

    @PostMapping(path = "/students/update/{studentId}")
    public String updateStudent(@PathVariable("studentId") @Min(0) Long studentId,
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
