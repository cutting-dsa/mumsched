package edu.mum.mumsched.auth.controller;

import edu.mum.mumsched.config.security.CustomUserDetails;
import edu.mum.mumsched.config.security.SecurityHelper;
import edu.mum.mumsched.users.model.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class DefaultController {

    @RequestMapping("/home")
    public String index(Model model) {

        // Logged In User
        /*SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            CustomUserDetails customUser = (CustomUserDetails) authentication.getPrincipal();
        }*/

        AppUser user = SecurityHelper.getLoggedInUser();

        return "layout";
    }

    // Login form
    @RequestMapping(value = {"/", "/login"})
    public String login() {
        return "/login";
    }

    // Login form with error
    @RequestMapping("/access_denied")
    public String loginError(Model model) {
        //model.addAttribute("loginError", true);
        return "error/403.html";
    }

   // static final String VIEW_INDEX = "index";

    /*@GetMapping(value = "/")
    public String getHome() {
        return VIEW_INDEX;
    }*/
}
