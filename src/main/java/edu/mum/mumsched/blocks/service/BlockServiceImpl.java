package edu.mum.mumsched.blocks.service;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.blocks.repository.BlockRepository;
import edu.mum.mumsched.entries.entity.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {
    private BlockRepository repository;

    @Autowired
    public BlockServiceImpl(BlockRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long create(Block block) throws Exception{
        List<Block> existingBlocks =  repository.findByDateBetween(block.getDate().withDayOfMonth(1),block.getDate().withDayOfMonth(block.getDate().lengthOfMonth()));
        if(existingBlocks.size() > 0) throw new Exception("A block for this date already exists");
        repository.save(block);
        return block.getId();
    }

    @Override
    public List<Block> getAllBlocks() {
        return repository.findByOrderByIdDesc();
    }

    @Override
    public void edit(Block block) throws Exception {
        if(!repository.existsById(block.getId())){
            throw new Exception("Block is invalid");
        }
        List<Block> existingBlocks =  repository.findByDateBetween(block.getDate().withDayOfMonth(1),block.getDate().withDayOfMonth(block.getDate().lengthOfMonth()));
        if(existingBlocks.stream().anyMatch(b-> !b.getId().equals(block.getId()))) throw new Exception("A block for this date already exists");
        repository.save(block);
    }

    @Override
    public void delete(Long id) throws Exception {
        if(!repository.existsById(id)){
            throw new Exception("Block is invalid");
        }
        repository.deleteById(id);
    }
}
