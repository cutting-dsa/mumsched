package edu.mum.mumsched.sectionenrollment.domain;

import edu.mum.mumsched.users.model.AppUser;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;
}
