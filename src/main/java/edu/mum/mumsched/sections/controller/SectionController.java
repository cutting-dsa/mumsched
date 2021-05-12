package edu.mum.mumsched.sections.controller;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.blocks.service.BlockService;
import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.faculty.service.FacultyService;
import edu.mum.mumsched.sections.model.Section;
import edu.mum.mumsched.sections.service.SectionService;
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
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private BlockService blockService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSections(Model model) {
        Collection<Section> sections = sectionService.getAllSections();
        model.addAttribute("sections", sections);
        return "sections/view";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {

        Collection<Faculty> faculties = facultyService.getAllFaculties();

        Collection<Block> blocks = blockService.getAllBlocks();

        model.addAttribute("section", new Section());
        model.addAttribute("blocks", blocks);
        model.addAttribute("faculties", faculties);

        return "sections/create";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") Long id) {
        Section section = sectionService.getSection(id);

        Collection<Faculty> faculties = facultyService.getAllFaculties();
        Collection<Block> blocks = blockService.getAllBlocks();

        model.addAttribute("section", section);
        model.addAttribute("blocks", blocks);
        model.addAttribute("faculties", faculties);
        return "sections/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("section") @Valid Section section,
                       BindingResult result,
                       Model model) {

        sectionService.save(section);

        return "redirect:/sections/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("section") @Valid Section section,
                         BindingResult result,
                         Model model, @PathVariable("id") Long id) {

        if(result.hasErrors()) {
            return "sections/edit";
        }
        section.setId(id);
        sectionService.edit(section);

        return "redirect:/sections/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id,
                         BindingResult result,
                         Model model) {

        if(result.hasErrors()) {
            return "sections/view";
        }

        sectionService.delete(id);

        return "redirect:/sections/";
    }
}
