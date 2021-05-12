package edu.mum.mumsched.faculty.controller;

import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.faculty.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

    @Autowired
    FacultyService facultyService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getFaculties(Model model) {
        Collection<Faculty> faculties = facultyService.getAllFaculties();
        model.addAttribute("faculties", faculties);
        return "faculties/view";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "faculties/create";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") Long id) {
        Faculty faculty = facultyService.getFaculty(id);

        faculty.setFirstName(faculty.getUser().getFirstName());
        faculty.setLastName(faculty.getUser().getLastName());
        faculty.setEmail(faculty.getUser().getEmail());

        model.addAttribute("faculty", faculty);
        return "faculties/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("faculty") @Valid Faculty faculty,
                       BindingResult result,
                       Model model) {

        facultyService.save(faculty);

        return "redirect:/faculties/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("faculty") @Valid Faculty faculty,
                         BindingResult result,
                         Model model, @PathVariable("id") Long id) {

        if(result.hasErrors()) {
            return "faculties/edit";
        }
        faculty.setId(id);
        facultyService.edit(faculty);

        return "redirect:/faculties/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id,
                             BindingResult result,
                             Model model) {

        if(result.hasErrors()) {
            return "faculties/view";
        }

        facultyService.delete(id);

        return "redirect:/faculties/";
    }

}
