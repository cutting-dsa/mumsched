package edu.mum.mumsched.facultycourses.service;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.blocks.service.BlockService;
import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.faculty.repository.FacultyRepository;
import edu.mum.mumsched.faculty.service.FacultyService;
import edu.mum.mumsched.facultycourses.model.FacultyCourse;
import edu.mum.mumsched.facultycourses.repository.FacultyCourseRepository;
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

    @Override
    public void save(FacultyCourse facultyCourse) {

        Faculty faculty = facultyService.getFaculty(facultyCourse.getFaculty().getId());

        Block block = blockService.getBlock(facultyCourse.getBlock().getId());

        facultyCourse.setFaculty(faculty);
        facultyCourse.setBlock(block);

        facultyCourseRepository.save(facultyCourseRepository);

    }

    @Override
    public void edit(FacultyCourse facultyCourse) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public FacultyCourse getFacultyCourse(Long id) {
        return null;
    }

    @Override
    public Collection<FacultyCourse> getAllFacultyCourses() {
        return null;
    }
}
