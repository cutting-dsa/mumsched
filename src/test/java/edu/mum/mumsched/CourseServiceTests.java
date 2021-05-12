package edu.mum.mumsched;

import edu.mum.mumsched.courses.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CourseServiceTests {
    CourseService courseService = mock(CourseService.class);

    @Test
    public void createCourseTest(){
        //Arrange //Act //Assert
        when(courseService.save(course)).thenReturn(course);


    }

}
