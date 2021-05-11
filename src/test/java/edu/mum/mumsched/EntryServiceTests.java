package edu.mum.mumsched;

import edu.mum.mumsched.entries.entity.Entry;
import edu.mum.mumsched.entries.repository.EntryRepository;
import edu.mum.mumsched.entries.service.EntryService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MumschedApplication.class)
public class EntryServiceTests {
    @Autowired
    EntryService service;

    @Autowired
    EntryRepository repo;

    @Test
    public void create_entry_successfully() throws Exception {
        Long id = service.create(new Entry("Feb", LocalDate.now(),10));
        Assert.assertNotNull(id);
    }

    @Test
    public void create_entry_duplicate_date() throws Exception {
        Long id = service.create(new Entry("Feb", LocalDate.of(2020,2,1),10));
        Assert.assertNotNull(id);

        assertThrows(Exception.class, () -> {
            service.create(new Entry("Feb", LocalDate.of(2020,2,29),10));
        });
    }

    @Test
    public void update_entry() throws Exception {
        Long id = service.create(new Entry("Feb", LocalDate.now().minusMonths(2),10));
        Assert.assertNotNull(id);

        Optional<Entry> entry = repo.findById(id);
        assertTrue(entry.isPresent());
        entry.get().setName("Feb 2");
        service.edit(entry.get());
    }
}
