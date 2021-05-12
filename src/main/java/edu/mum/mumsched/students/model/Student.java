package edu.mum.mumsched.students.model;

import edu.mum.mumsched.entries.entity.Entry;
import edu.mum.mumsched.sectionenrollment.domain.Section;
import edu.mum.mumsched.users.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_active=1")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "registration_number", nullable = false, unique = true)
    private BigInteger registrationNumber;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private AppUser createdBy;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_id", referencedColumnName = "id")
    private Entry entry;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Column(name = "has_registered_courses", nullable = false)
    private Boolean hasRegisteredCourses;

    // bi-directional many-to-many association to Section
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "student_section", joinColumns = {@JoinColumn(name = "student_id")}, inverseJoinColumns = {@JoinColumn(name = "section_id")})
    private Collection<Section> sections;
}
