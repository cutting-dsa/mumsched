package edu.mum.mumsched.users.service;

import edu.mum.mumsched.config.exceptions.NotFoundException;
import edu.mum.mumsched.users.model.AppUser;
import edu.mum.mumsched.users.repository.AppUserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService{

    @Autowired
    private AppUserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public void save(AppUser user) {
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);

        userRepository.save(user);
    }

    @Override
    public void edit(AppUser user) {
        // check user
        AppUser oldUser = this.getUser(user.getId());

        userRepository.save(user);
    }

    @Override
    public void deactivate(Long id) {
        // check user
        AppUser user = this.getUser(id);
        user.setActive(false);

        userRepository.save(user);
    }

    @Override
    public AppUser getUser(Long id) {
        Optional<AppUser> user = userRepository.findById(id);

        return user.orElseThrow(() -> new NotFoundException("User with id " + id + " cannot be found"));
    }

    @Override
    public Collection<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Collection<AppUser> getActiveUsers() {
        return userRepository.findAllByActiveIsTrue();
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
