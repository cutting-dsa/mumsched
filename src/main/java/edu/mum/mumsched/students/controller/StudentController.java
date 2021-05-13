package edu.mum.mumsched.students.controller;

import edu.mum.mumsched.config.security.SecurityHelper;
import edu.mum.mumsched.entries.entity.Entry;
import edu.mum.mumsched.entries.service.EntryService;
import edu.mum.mumsched.students.model.AddStudentForm;
import edu.mum.mumsched.students.model.DeleteStudentParameters;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.model.Track;
import edu.mum.mumsched.students.service.StudentService;
import edu.mum.mumsched.users.model.AppUser;
import edu.mum.mumsched.users.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AppUserService userService;

    @Autowired
    private EntryService entryService;

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
        AddStudentForm student = new AddStudentForm();
        List<Entry> entries = entryService.getAllEntries();
        model.addAttribute("student", student);
        model.addAttribute("entries", entries);
        model.addAttribute("tracks", Track.values());
        return "students/create";
    }

    @PostMapping("/students")
    public String processStudent(@Valid @ModelAttribute("student") AddStudentForm student,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "students/create";
        }

        Entry entry = entryService.getEntryById(student.getEntryId());
        AppUser newUser = student.toUser();
        userService.save(newUser);
        AppUser user = userService.getUserByEmail(newUser.getEmail());
        AppUser loggedUser = SecurityHelper.getLoggedInUser();

        studentService.createStudent(entry, student.getTrack(), user, loggedUser);

        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    @Validated
    public String showUpdateForm(@PathVariable @Min(0) Long id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "students/view";
    }

    @PostMapping(path = "/students/update/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId,
                                @Valid Student student,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            student.setId(studentId);
            return "students/view";
        }
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
