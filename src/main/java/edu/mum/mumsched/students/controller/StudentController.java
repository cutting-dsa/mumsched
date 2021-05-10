package edu.mum.mumsched.students.controller;

import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.service.StudentService;
import edu.mum.mumsched.users.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Controller
@RequestMapping(name = "/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "students/all";
    }

    @GetMapping("/{id}")
    @Validated
    public String getStudent(@PathVariable @Min(1) Long id, Model model) {
        model.addAttribute("student", studentService.getStudent(id));
        return "students/view";
    }

    @PostMapping
    public String processStudent(@Valid Student student,
                                 Errors errors,
                                 SessionStatus sessionStatus,
                                 @AuthenticationPrincipal AppUser user) {
        if (errors.hasErrors()) {
            return "create";
        }

        student.setUser(user);
        studentService.save(student);
        sessionStatus.setComplete();

        return "redirect:/all";
    }

    @PatchMapping(path = "/{studentId}")
    public String patchStudent(@PathVariable("studentId") @Min(1) Long studentId,
                               @RequestBody Student student, Model model) {
        model.addAttribute("student", studentService.updateStudent(studentId, student));
        return "students/view";
    }

    @DeleteMapping("/studentId")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long studentId) {
        try {
            Student student = studentService.getStudent(studentId);
            studentService.deleteStudent(student);
        } catch (EmptyResultDataAccessException e) {
        }
    }
}
