package edu.mum.mumsched.entries.service;

import edu.mum.mumsched.entries.entity.Entry;

import java.util.List;

public interface EntryService {
    Long create(Entry entry) throws Exception;
    List<Entry> getAllEntries();
    void edit(Entry entry) throws Exception;
    void delete(Long id) throws Exception;
    Entry getEntryById(Long id);
}
