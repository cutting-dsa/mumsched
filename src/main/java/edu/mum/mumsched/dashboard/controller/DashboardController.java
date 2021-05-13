package edu.mum.mumsched.dashboard.controller;

import edu.mum.mumsched.config.security.SecurityHelper;
import edu.mum.mumsched.courses.entity.Course;
import edu.mum.mumsched.courses.service.CourseService;
import edu.mum.mumsched.dashboard.service.DashboardService;
import edu.mum.mumsched.entries.entity.Entry;
import edu.mum.mumsched.entries.service.EntryService;
import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.faculty.service.FacultyService;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.service.StudentService;
import edu.mum.mumsched.users.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

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

    @Autowired
    DashboardService dashboardService;


    @RequestMapping("/dashboard")
    public String getDashboard(Model model) {

        AppUser loggedUser = SecurityHelper.getLoggedInUser();
        if (loggedUser.getRole().equals("STUDENT")) {
            fetchStudentData(model, loggedUser);
        } else {
            fetchAdminData(model);
        }

        return "dashboard";
    }

    private void fetchStudentData(Model model, AppUser appUser) {
        Student student = studentService.getStudentByUserId(appUser.getId());
        model.addAttribute("sections", student.getSections());
        model.addAttribute("student", student);
    }

    private void fetchAdminData(Model model) {
        List<Student> studentList = studentService.getStudents();
        Collection<Faculty> facultyList = facultyService.getAllFaculties();
        List<Entry> entryList = entryService.getAllEntries();
        Collection<Course> courseList = courseService.getAllCourses();

        model.addAttribute("studentList", studentList);
        model.addAttribute("facultyList", facultyList);
        model.addAttribute("entryList", entryList);
        model.addAttribute("courseList", courseList);

        model.addAttribute("chartData", dashboardService.getEntryChartData());
        model.addAttribute("sectionData", dashboardService.getSectionChartData());
    }
}
