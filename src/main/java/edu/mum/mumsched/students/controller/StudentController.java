package edu.mum.mumsched.students.controller;

import edu.mum.mumsched.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(name = "/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "students/view";
    }
}
