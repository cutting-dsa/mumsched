package edu.mum.mumsched.facultycourses.service;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.blocks.service.BlockService;
import edu.mum.mumsched.config.exceptions.NotFoundException;
import edu.mum.mumsched.config.security.SecurityHelper;
import edu.mum.mumsched.courses.entity.Course;
import edu.mum.mumsched.courses.service.CourseService;
import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.faculty.service.FacultyService;
import edu.mum.mumsched.facultycourses.model.FacultyCourse;
import edu.mum.mumsched.facultycourses.repository.FacultyCourseRepository;
import edu.mum.mumsched.users.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyCourseServiceImpl implements FacultyCourseService{

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private BlockService blockService;

    @Autowired
    private FacultyCourseRepository facultyCourseRepository;

    @Autowired
    private CourseService courseService;

    @Override
    public void save(FacultyCourse facultyCourse) {

        AppUser user = SecurityHelper.getLoggedInUser();

        Faculty faculty = facultyService.getFacultyByUserId(user.getId());

        Block block = blockService.getBlock(facultyCourse.getBlock().getId());

        Course course = courseService.getCourse(facultyCourse.getCourse().getId());

        facultyCourse.setFaculty(faculty);
        facultyCourse.setBlock(block);
        facultyCourse.setCourse(course);

        facultyCourseRepository.save(facultyCourse);
    }

    @Override
    public void edit(FacultyCourse facultyCourse) {
        FacultyCourse facultyCourseDB = this.getFacultyCourse(facultyCourse.getId());

        Block block = blockService.getBlock(facultyCourse.getBlock().getId());

        Course course = courseService.getCourse(facultyCourse.getCourse().getId());

        facultyCourse.setFaculty(facultyCourseDB.getFaculty());
        facultyCourse.setBlock(block);
        facultyCourse.setCourse(course);

        facultyCourseRepository.save(facultyCourse);
    }

    @Override
    public void delete(Long id) {
        FacultyCourse facultyCourse = this.getFacultyCourse(id);

        facultyCourseRepository.deleteById(id);
    }

    @Override
    public FacultyCourse getFacultyCourse(Long id) {
        return facultyCourseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Faculty Course selection with id " + id + " is not found"));
    }

    @Override
    public Collection<FacultyCourse> getAllFacultyCourses() {
        return facultyCourseRepository.findAll();
    }
}
