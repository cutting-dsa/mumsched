package edu.mum.mumsched.students.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import edu.mum.mumsched.users.model.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Data
@NoArgsConstructor
@Where(clause="is_active=1")
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank(message = "Student Registration Number cannot be blank")
    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    @Column(name="is_active")
    private Boolean active;
}
