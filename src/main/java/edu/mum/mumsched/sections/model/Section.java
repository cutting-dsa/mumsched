package edu.mum.mumsched.sections.model;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.faculty.model.Faculty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

}
