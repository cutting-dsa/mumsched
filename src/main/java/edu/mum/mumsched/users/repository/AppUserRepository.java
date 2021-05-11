package edu.mum.mumsched.users.repository;

import edu.mum.mumsched.users.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    @Query("SELECT u FROM AppUser u WHERE u.email = ?1")
    AppUser findByEmail(String email);

    Collection<AppUser> findAllByActiveIsTrue();
}
