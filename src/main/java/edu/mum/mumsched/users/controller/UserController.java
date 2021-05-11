package edu.mum.mumsched.users.controller;

import edu.mum.mumsched.users.model.AppUser;
import edu.mum.mumsched.users.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AppUserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        Collection<AppUser> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/view";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") Long id ) {
        //AppUser user = userService.getUser(id);
        model.addAttribute("users", null);
        return "users/edit";
    }


}
