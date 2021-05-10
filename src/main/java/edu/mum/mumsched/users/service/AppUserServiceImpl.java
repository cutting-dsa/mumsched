package edu.mum.mumsched.users.service;

import edu.mum.mumsched.users.model.AppUser;
import edu.mum.mumsched.users.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AppUserServiceImpl implements AppUserService{

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public void save(AppUser user) {
        userRepository.save(user);
    }

    @Override
    public void edit(AppUser user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public AppUser getUser(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User with id : " + id + " not found"));
    }

    @Override
    public Collection<AppUser> getUsers() {
        return null;
    }
}
