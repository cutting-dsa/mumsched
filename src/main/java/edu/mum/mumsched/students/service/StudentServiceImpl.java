package edu.mum.mumsched.students.service;

import edu.mum.mumsched.core.BadRequestException;
import edu.mum.mumsched.entries.entity.Entry;
import edu.mum.mumsched.students.model.AtomicBigInteger;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.model.Track;
import edu.mum.mumsched.students.repository.StudentRepository;
import edu.mum.mumsched.users.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(Long id) {
        if (id == null) throw new BadRequestException("Student id cannot be null");
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student getStudentByUserId(Long id) {
        return studentRepository.getStudentByUserId(id);
    }

    @Override
    public Student updateStudent(Long id, Student patch) {
        Student student = studentRepository.getOne(id);

        if (student.getRegistrationNumber() != null) {
            student.setRegistrationNumber(patch.getRegistrationNumber());
        }

        if (student.getUser() != null) {
            student.setUser(patch.getUser());
        }

        return studentRepository.save(student);
    }

    @Override
    public Student deleteStudent(Student student) {
        if (student != null) {
            student.setActive(false);
            return studentRepository.save(student);
        } else {
            return null;
        }
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public BigInteger generateRegistrationNumber() {
        BigInteger recentReg = studentRepository.getMostRecentRegistration();
        if (recentReg == null) {
            return new BigInteger("1");
        }
        recentReg.add(new BigInteger("1"));
        return recentReg;
    }

    @Override
    public Student createStudent(Entry entry, Track track, AppUser user, AppUser loggedUser) {
        Student newStudent = new Student();
        newStudent.setUser(user);
        newStudent.setActive(true);
        BigInteger incremented = newRegistrationNumber();
        newStudent.setRegistrationNumber(incremented);
        newStudent.setEntry(entry);
        newStudent.setCreatedBy(loggedUser);
        newStudent.setHasRegisteredCourses(true);
        newStudent.setTrack(track.name());
        return save(newStudent);
    }

    @Override
    public BigInteger newRegistrationNumber() {
        BigInteger recentRegNo = generateRegistrationNumber();
        AtomicBigInteger atomicBigInteger = new AtomicBigInteger(recentRegNo);
        return atomicBigInteger.incrementAndGet();
    }
}
