package edu.mum.mumsched;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.blocks.repository.BlockRepository;
import edu.mum.mumsched.blocks.service.BlockService;
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
public class BlockServiceTests {
    @Autowired
    BlockService service;

    @Autowired
    BlockRepository repo;

    @Test
    public void create_entry_successfully() throws Exception {
        repo.deleteAll();
        Long id = service.create(new Block("Feb",LocalDate.now().minusMonths(2),LocalDate.now(),LocalDate.now().plusMonths(1)));
        Assertions.assertNotNull(id);
    }

    @Test
    public void create_entry_duplicate_date() throws Exception {
        repo.deleteAll();
        Long id = service.create(new Block("Feb", LocalDate.of(2019,2,1),LocalDate.now(),LocalDate.now().plusMonths(1)));
        Assert.assertNotNull(id);

        assertThrows(Exception.class, () -> {
            service.create(new Block("Feb", LocalDate.of(2019,2,10),LocalDate.now(),LocalDate.now().plusMonths(1)));
        });
    }

    @Test
    public void update_entry() throws Exception {
        repo.deleteAll();
        Long id = service.create(new Block("Feb",LocalDate.of(2016,2,10).minusMonths(2),LocalDate.now(),LocalDate.now().plusMonths(1)));
        Assertions.assertNotNull(id);

        Optional<Block> block = repo.findById(id);
        assertTrue(block.isPresent());
        block.get().setName("Feb 2");
        service.edit(block.get());
    }
}
