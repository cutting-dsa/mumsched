package edu.mum.mumsched.entries.repository;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.entries.entity.Entry;
import edu.mum.mumsched.users.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {
    List<Entry> findByOrderByIdDesc();
    Entry findByDate(LocalDate date);
    List<Entry> findByDateBetween(LocalDate from, LocalDate to);
}
