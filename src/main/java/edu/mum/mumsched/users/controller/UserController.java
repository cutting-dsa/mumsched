package edu.mum.mumsched.users.controller;

import edu.mum.mumsched.config.exceptions.GeneralException;
import edu.mum.mumsched.users.model.AppUser;
import edu.mum.mumsched.users.model.Role;
import edu.mum.mumsched.users.service.AppUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AppUserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getActiveUsers(Model model) {
        Collection<AppUser> users = userService.getActiveUsers();
        model.addAttribute("users", users);
        return "users/view";
    }

    @SneakyThrows
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") Long id ) {
        AppUser user = userService.getUser(id);
        model.addAttribute("appUser", user);
        return "users/edit";
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("appUser", new AppUser());
        model.addAttribute("roles", Arrays.asList(Role.values()));
        return "users/create";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("appUser") @Valid AppUser user,
                       BindingResult result,
                       Model model) {

        try {
            userService.save(user);
        }catch (Exception ex){
            throw new GeneralException(ex.getMessage());
        }

        return "redirect:/users/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("appUser") @Valid AppUser user,
                       BindingResult result,
                       Model model, @PathVariable("id") Long id) {

        if(result.hasErrors()) {
            //log.info("I have reached here");
            return "users/edit";
        }

        //user.setId(id);
        userService.edit(user);

        return "redirect:/users/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deactivate(@PathVariable("id") Long id,
                       BindingResult result,
                       Model model) {

        if(result.hasErrors()) {
            //log.info("I have reached here");
            return "users/view";
        }

        userService.deactivate(id);

        return "redirect:/users/";
    }


}
