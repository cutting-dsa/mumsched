package edu.mum.mumsched.faculty.repository;

import edu.mum.mumsched.faculty.model.Faculty;
import edu.mum.mumsched.users.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Faculty getFacultyByUser(AppUser user);

    //@Query("Select f from Faculty f where f.user=:id")
    Faculty getFacultyByUserId(Long id);
}
