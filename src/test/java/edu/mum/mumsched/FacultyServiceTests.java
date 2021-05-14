package edu.mum.mumsched;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.blocks.repository.BlockRepository;
import edu.mum.mumsched.blocks.service.BlockService;
import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.faculty.repository.FacultyRepository;
import edu.mum.mumsched.faculty.service.FacultyService;
import edu.mum.mumsched.users.model.AppUser;
import edu.mum.mumsched.users.model.Role;
import edu.mum.mumsched.users.repository.AppUserRepository;
import edu.mum.mumsched.users.service.AppUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MumschedApplication.class)
public class FacultyServiceTests {
    @Autowired
    FacultyService facultyService;

    @Autowired
    AppUserService appUserService;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Test
    public void create_faculty_successfully() throws Exception {
        facultyRepository.deleteAll();
        appUserRepository.deleteAll();

        Faculty faculty = new Faculty();
        //faculty.setUser(appUser);
        faculty.setEmail("kalema@miu.edu");
        faculty.setFirstName("Test");
        faculty.setLastName("Faculty");
        faculty.setBiography("I am a machine learning expert");
        faculty.setStaffId((long) gen());
        faculty.setStartDate(LocalDate.now());
        faculty.setEndDate(faculty.getStartDate().plusYears(2));

        Long id = facultyService.save(faculty);

        Assertions.assertNotNull(id);
    }

    @Test
    public void create_faculty_test_user_account_created_successfully() throws Exception {
        facultyRepository.deleteAll();
        appUserRepository.deleteAll();

        Faculty faculty = new Faculty();
        //faculty.setUser(appUser);
        faculty.setEmail("kalema@miu.edu");
        faculty.setFirstName("Test");
        faculty.setLastName("Faculty");
        faculty.setBiography("I am a machine learning expert");
        faculty.setStaffId((long) gen());
        faculty.setStartDate(LocalDate.now());
        faculty.setEndDate(faculty.getStartDate().plusYears(2));

        Long id = facultyService.save(faculty);

        Assertions.assertNotNull(id);

        Faculty savedFaculty = facultyService.getFaculty(id);

        Assertions.assertTrue(savedFaculty != null);
        Assertions.assertTrue(savedFaculty.getUser() != null);
        Assertions.assertEquals(savedFaculty.getUser().getEmail(), faculty.getUser().getEmail());
    }


    @Test
    public void create_faculty_duplicate_staffId() throws Exception {
        facultyRepository.deleteAll();
        appUserRepository.deleteAll();

        Faculty faculty = new Faculty();
        faculty.setEmail("kalema@miu.edu");
        faculty.setFirstName("Test");
        faculty.setLastName("Faculty");
        faculty.setBiography("I am a machine learning expert");
        faculty.setStaffId((long) gen());
        faculty.setStartDate(LocalDate.now());
        faculty.setEndDate(faculty.getStartDate().plusYears(2));

        Long id = facultyService.save(faculty);

        Assertions.assertNotNull(id);

        Faculty newFaculty = faculty;

        assertThrows(Exception.class, () -> {
            facultyService.save(newFaculty);
        });
    }

    @Test
    public void update_entry() throws Exception {
        facultyRepository.deleteAll();
        appUserRepository.deleteAll();

        Faculty faculty = new Faculty();
        //faculty.setUser(appUser);
        faculty.setEmail("kalema@miu.edu");
        faculty.setFirstName("Test");
        faculty.setLastName("Faculty");
        faculty.setBiography("I am a machine learning expert");
        faculty.setStaffId((long) gen());
        faculty.setStartDate(LocalDate.now());
        faculty.setEndDate(faculty.getStartDate().plusYears(2));

        Long id = facultyService.save(faculty);

        Assertions.assertNotNull(id);

        Optional<Faculty> facultyDB = facultyRepository.findById(id);
        assertTrue(facultyDB.isPresent());
        facultyDB.get().setStaffId((long) gen());
        facultyService.edit(facultyDB.get());

    }

    private int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 1000000 + r.nextInt(10000));
    }
}
