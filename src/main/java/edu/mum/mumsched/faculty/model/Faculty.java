package edu.mum.mumsched.faculty.model;

import edu.mum.mumsched.users.model.AppUser;
import edu.mum.mumsched.users.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "staffId", unique = true, nullable = false)
    private Long staffId;

    @Transient
    private transient String firstName;

    @Transient
    private transient String lastName;

    @Transient
    private transient String email;

    @Column(name = "biography")
    @NotNull
    private String biography;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    public AppUser createAppUser(){
        AppUser appUser = new AppUser();
        appUser.setEmail(email);
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setActive(true);
        appUser.setRole(Role.FACULTY.name());
        appUser.setPassword("password");

        return appUser;
    }

    public AppUser editAppUser(){
        AppUser appUser = new AppUser();
        appUser.setEmail(email);
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setActive(true);
        appUser.setRole(Role.FACULTY.name());

        return appUser;
    }





}
