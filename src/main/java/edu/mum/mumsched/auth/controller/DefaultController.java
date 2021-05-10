package edu.mum.mumsched.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {

    @RequestMapping("/index")
    public String index() {
        //return "That's pretty basic!";
        return "home";
    }

    // Login form
    @RequestMapping("/")
    public String login() {
        return "redirect:/login";
    }

    // Login form with error
    @RequestMapping("/access_denied")
    public String loginError(Model model) {
        //model.addAttribute("loginError", true);
        return "error/403.html";
    }

}
