package edu.mum.mumsched.blocks.repository;

import edu.mum.mumsched.blocks.entity.Block;
import jdk.vm.ci.meta.Local;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {
    List<Block> findByOrderByIdDesc();

    List<Block> findByDateBetween(LocalDate from, LocalDate to);

    Block findByDate(LocalDate date);

    List<Block> findBlockByDateAfter(LocalDate from);

    List<Block> findTopByDateAfter(Integer limit, LocalDate from);
    //5 for mpp and 6 for fpp
}
