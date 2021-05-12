package edu.mum.mumsched.entries.service;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.entries.entity.Entry;
import edu.mum.mumsched.entries.repository.EntryRepository;
import edu.mum.mumsched.users.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService{
    private EntryRepository repository;

    @Autowired
    public EntryServiceImpl(EntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long create(Entry entry) throws Exception{
        List<Entry> existingEntries =  repository.findByDateBetween(entry.getDate().withDayOfMonth(1),entry.getDate().withDayOfMonth(entry.getDate().lengthOfMonth()));
        if(existingEntries.size() > 0) throw new Exception("An entry for this date already exists");
        repository.save(entry);
        return entry.getId();
    }

    @Override
    public List<Entry> getAllEntries() {
        return repository.findByOrderByIdDesc();
    }

    @Override
    public void edit(Entry entry) throws Exception {
        if(!repository.existsById(entry.getId())){
            throw new Exception("Entry is invalid");
        }
        List<Entry> existingEntries =  repository.findByDateBetween(entry.getDate().withDayOfMonth(1),entry.getDate().withDayOfMonth(entry.getDate().lengthOfMonth()));
        if(existingEntries.stream().anyMatch(b-> !b.getId().equals(entry.getId()))) throw new Exception("An entry for this date already exists");
        repository.save(entry);
    }

    @Override
    public void delete(Long id) throws Exception {
        if(!repository.existsById(id)){
            throw new Exception("Entry is invalid");
        }
        repository.deleteById(id);
    }

    @Override
    public Entry getEntryById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
