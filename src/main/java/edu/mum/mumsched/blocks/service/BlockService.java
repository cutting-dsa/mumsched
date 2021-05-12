package edu.mum.mumsched.blocks.service;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.entries.entity.Entry;

import java.util.List;

public interface BlockService {
    Long create(Block block) throws Exception;
    List<Block> getAllBlocks();
    void edit(Block entry) throws Exception;
    void delete(Long id) throws Exception;
    Block getBlock(Long id);
    List<Block> getBlocksByEntry(Entry entry);
}
