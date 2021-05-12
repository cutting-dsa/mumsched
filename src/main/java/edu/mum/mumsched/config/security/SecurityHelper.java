package edu.mum.mumsched.config.security;

import edu.mum.mumsched.config.exceptions.NotFoundException;
import edu.mum.mumsched.users.model.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityHelper {

    public static AppUser getLoggedInUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            CustomUserDetails customUser = (CustomUserDetails) authentication.getPrincipal();
            return customUser.getUser();
        }else{
            //throw new NotFoundException("Logged In Use not Found");
            return new AppUser();
        }
    }
}
