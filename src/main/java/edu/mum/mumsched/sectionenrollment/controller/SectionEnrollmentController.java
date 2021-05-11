package edu.mum.mumsched.sectionenrollment.controller;

import edu.mum.mumsched.sectionenrollment.service.SectionEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SectionEnrollmentController {

    @Autowired
    private SectionEnrollmentService sectionEnrollmentService;

    @RequestMapping("/sections")
    public String index(Model model) {
        model.addAttribute("sections",sectionEnrollmentService.getAllSections());
        return "enrollment/sections";
    }

}
