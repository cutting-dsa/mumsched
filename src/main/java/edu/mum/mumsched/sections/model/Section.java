package edu.mum.mumsched.sections.model;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.courses.entity.Course;
import edu.mum.mumsched.faculty.model.Faculty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "block_id", referencedColumnName = "id")
    private Block block;

    @Column(name = "number_of_seats")
    private Long numberOfSeats;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @Column(name = "number_of_taken_seats")
    private Long numberOfTakenSeats;


}
