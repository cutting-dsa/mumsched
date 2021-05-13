package edu.mum.mumsched.dashboard.service;

import edu.mum.mumsched.entries.entity.Entry;
import edu.mum.mumsched.entries.service.EntryService;
import edu.mum.mumsched.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    StudentService studentService;

    @Autowired
    EntryService entryService;

    @Override
    public Map<String, Long> getSectionChartData() {
        return studentService
                .getStudents()
                .stream()
                .flatMap(student -> student.getSections()
                        .stream())
                .collect(Collectors.groupingBy(section -> section.getCourse().getName(), Collectors.counting()));
    }

    @Override
    public Map<String, Long> getEntryChartData() {
        return entryService
                .getAllEntries()
                .stream()
                .collect(Collectors.groupingBy((Entry::getName), Collectors.summingLong(Entry::getCapacity)));
    }
}
