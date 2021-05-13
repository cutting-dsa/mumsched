package edu.mum.mumsched.dashboard;

import edu.mum.mumsched.courses.entity.Course;
import edu.mum.mumsched.courses.service.CourseService;
import edu.mum.mumsched.entries.entity.Entry;
import edu.mum.mumsched.entries.service.EntryService;
import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.faculty.service.FacultyService;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class DashboardController {

    @Autowired
    StudentService studentService;

    @Autowired
    FacultyService facultyService;

    @Autowired
    EntryService entryService;

    @Autowired
    CourseService courseService;

    @RequestMapping("/dashboard")
    public String getDashboard(Model model) {
        List<Student> studentList = studentService.getStudents();
        Collection<Faculty> facultyList = facultyService.getAllFaculties();
        List<Entry> entryList = entryService.getAllEntries();
        Collection<Course> courseList = courseService.getAllCourses();

        model.addAttribute("studentList", studentList);
        model.addAttribute("facultyList", facultyList);
        model.addAttribute("entryList", entryList);
        model.addAttribute("courseList", courseList);

        return "dashboard";
    }

}
