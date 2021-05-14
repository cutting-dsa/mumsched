package edu.mum.mumsched.students;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.courses.entity.Course;
import edu.mum.mumsched.entries.entity.Entry;
import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.sections.model.Section;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.model.Track;
import edu.mum.mumsched.users.model.AppUser;
import edu.mum.mumsched.users.model.Role;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;

public class Factory {
    public static Student createStudent() {
        return new Student(1L, new BigInteger("1234"),
                createStudentUser(),
                createAdminUser(),
                createEntry(),
                true,
                true,
                Arrays.asList(createSection()),
                Track.MPP.name());
    }

    public static Faculty createFaculty() {
        return new Faculty();
    }

    public static AppUser createStudentUser() {
        return new AppUser(1L, "johnolwamba@gmail.com", "Johnstone", "Ananda", "secret", true, createStudentRole().name());
    }

    public static AppUser createAdminUser() {
        return new AppUser(1L, "admin@gmail.com", "JohnstoneAdmin", "Ananda", "secret", true, createAdminRole().name());
    }

    public static AppUser createFacultyUser() {
        return new AppUser(1L, "faculty@gmail.com", "JohnstoneAdmin", "Ananda", "secret", true, createFacultyRole().name());
    }

    public static Role createStudentRole() {
        return Role.STUDENT;
    }

    public static Role createAdminRole() {
        return Role.ADMIN;
    }

    public static Role createFacultyRole() {
        return Role.FACULTY;
    }

    public static Entry createEntry() {
        return new Entry("Feb", LocalDate.of(2020, 2, 1), 10);
    }

    public static Block createBlock() {
        return new Block("Feb", LocalDate.of(2016, 2, 10).minusMonths(2), LocalDate.now(), LocalDate.now().plusMonths(1));
    }

    public static Course createCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setName("MPP");
        course.setCode("309");
        course.setDescription("Desc");
        return course;
    }

    public static Section createSection() {
        return new Section(1L,createFaculty(), createBlock(), 100L, createCourse(), 1L);
    }
}
