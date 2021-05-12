package edu.mum.mumsched.sectionenrollment.domain;

import edu.mum.mumsched.blocks.entity.Block;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "block_id", referencedColumnName = "id")
    private Block block;

    @Column(name = "seats", nullable = false)
    private int seats;
}
