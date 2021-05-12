package edu.mum.mumsched.users.service;

import edu.mum.mumsched.users.model.AppUser;

import java.util.Collection;


public interface AppUserService {

    AppUser save(AppUser user);
    void edit(AppUser user);
    void deactivate(Long id);
    AppUser getUser(Long id) throws Exception;
    Collection<AppUser> getAllUsers();
    Collection<AppUser> getActiveUsers();
    AppUser getUserByEmail(String email);
}
