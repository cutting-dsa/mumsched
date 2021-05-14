package edu.mum.mumsched;

import edu.mum.mumsched.courses.service.CourseService;
import edu.mum.mumsched.courses.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
class CourseServiceTest {
    @Mock
    private CourseService courseServiceMock;

    @InjectMocks
    private CourseServiceImpl courseServiceImpl;
    @Test
    void getAllCourses() {
        CourseServiceImpl
    }

    @Test
    void save() {
    }

    @Test
    void updateCourse() {
    }

    @Test
    void deleteCourse() {
    }

    @Test
    void getCourse() {
    }
}