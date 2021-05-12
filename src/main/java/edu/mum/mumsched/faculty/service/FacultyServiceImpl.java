package edu.mum.mumsched.faculty.service;

import edu.mum.mumsched.config.exceptions.NotFoundException;
import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.faculty.repository.FacultyRepository;
import edu.mum.mumsched.users.model.AppUser;
import edu.mum.mumsched.users.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;

@Service
public class FacultyServiceImpl implements FacultyService{

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    AppUserService userService;

    @Override
    public void save(Faculty faculty) {
        // save user
        AppUser user = userService.save(faculty.createAppUser());

        faculty.setUser(user);
        faculty.setStaffId((long) gen());

        facultyRepository.save(faculty);
    }

    @Override
    public void edit(Faculty faculty) {
        Faculty facultyDB = this.getFaculty(faculty.getId());

        faculty.setStaffId(facultyDB.getStaffId());
        faculty.setUser(facultyDB.getUser());

        facultyRepository.save(faculty);
    }

    @Override
    public void delete(Long id) {
        Faculty faculty = this.getFaculty(id);
        facultyRepository.delete(faculty);
    }

    @Override
    public Faculty getFaculty(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);

        return faculty.orElseThrow(() -> new NotFoundException("Faculty with id " + id + " cannot be found"));
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    private int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}
