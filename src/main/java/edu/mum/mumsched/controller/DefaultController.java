package edu.mum.mumsched.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "That's pretty basic!";
    }

    // Login form
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/access_denied")
    public String loginError(Model model) {
        //model.addAttribute("loginError", true);
        return "error/403.html";
    }

    // home
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

}
