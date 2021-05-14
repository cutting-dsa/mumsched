package edu.mum.mumsched.students.controller;

import edu.mum.mumsched.students.Factory;
import edu.mum.mumsched.students.model.AddStudentForm;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    public void testStudentList() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(Factory.createStudent());
        students.add(Factory.createStudent());

        when(studentService.getStudents()).thenReturn(students);
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/all"))
                .andExpect(model().attribute("students", hasSize(2)));
    }

    @Test
    public void testGetStudent() throws Exception {
        Long id = 1L;
        when(studentService.getStudent(id)).thenReturn(Factory.createStudent());

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/view"))
                .andExpect(model().attribute("student", instanceOf(Student.class)));
    }

    @Test
    public void testNewStudent() throws Exception {
        verifyZeroInteractions(studentService);
        mockMvc.perform(get("/students/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/create"))
                .andExpect(model().attribute("student", instanceOf(AddStudentForm.class)));

    }
}