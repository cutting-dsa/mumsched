package edu.mum.mumsched.sections.service;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.blocks.service.BlockService;
import edu.mum.mumsched.config.exceptions.NotFoundException;
import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.faculty.service.FacultyService;
import edu.mum.mumsched.sections.model.Section;
import edu.mum.mumsched.sections.repository.SectionRepository;
import edu.mum.mumsched.students.model.Student;
import edu.mum.mumsched.students.service.StudentService;
import edu.mum.mumsched.users.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private BlockService blockService;

    @Autowired
    private StudentService studentService;

    @Override
    public void save(Section section) {

        Faculty faculty = facultyService.getFaculty(section.getFaculty().getId());

        Block block = blockService.getBlock(section.getBlock().getId());

        section.setFaculty(faculty);
        section.setBlock(block);

        sectionRepository.save(section);

    }

    @Override
    public void edit(Section section) {

        Section sectionDB = this.getSection(section.getId());

        Faculty faculty = facultyService.getFaculty(section.getFaculty().getId());

        Block block = blockService.getBlock(section.getBlock().getId());

        section.setFaculty(faculty);
        section.setBlock(block);

        sectionRepository.save(section);

    }

    @Override
    public void delete(Long id) {
        Section section = this.getSection(id);

        sectionRepository.deleteById(id);
    }

    @Override
    public Section getSection(Long id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Section with id " + id + " is not found"));
    }

    @Override
    public Collection<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public List<Student> getSectionMembers(Long id) {
        Section section = getSection(id);
        return studentService.getStudents()
                .stream()
                .filter(student -> student.getSections().contains(section))
                .collect(Collectors.toList());
    }
}
