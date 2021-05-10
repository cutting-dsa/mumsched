package edu.mum.mumsched.users.controller;

import edu.mum.mumsched.users.model.AppUser;
import edu.mum.mumsched.users.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private AppUserService userService;

    @RequestMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users/view";
    }
}
