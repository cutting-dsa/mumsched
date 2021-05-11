package edu.mum.mumsched.blocks.repository;

import edu.mum.mumsched.blocks.entity.Block;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {
    List<Block> findByOrderByIdDesc();
    List<Block> findByDateBetween(LocalDate from, LocalDate to);
    Block findByDate(LocalDate date);
}
