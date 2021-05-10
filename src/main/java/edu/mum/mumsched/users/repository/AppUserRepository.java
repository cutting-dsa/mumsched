package edu.mum.mumsched.users.repository;

import edu.mum.mumsched.users.model.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    @Query("SELECT u FROM AppUser u WHERE u.email = ?1")
    AppUser findByEmail(String email);
}
