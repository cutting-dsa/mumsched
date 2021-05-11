package edu.mum.mumsched.blocks.controller;

import edu.mum.mumsched.blocks.entity.Block;
import edu.mum.mumsched.blocks.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlockController {

    BlockService service;

    @Autowired
    public BlockController(BlockService service) {
        this.service = service;
    }

    @RequestMapping("/blocks")
    public String index(Model model) {
        model.addAttribute("blocks",service.getAllBlocks());
        return "blocks/list";
    }

    @PostMapping("/blocks")
    public String addNewBlock(@ModelAttribute Block block, Model model) {

        try {
            service.create(block);
        } catch (Exception e){
            model.addAttribute("errorMessage",e.getMessage());
        }

        model.addAttribute("blocks",service.getAllBlocks());
        return "blocks/list";
    }

    @PostMapping("/blocks/edit")
    public String editBlock(@ModelAttribute Block block, Model model) {

        try {
            service.edit(block);
            return "redirect:/blocks";
        } catch (Exception e){
            model.addAttribute("errorMessage",e.getMessage());
            return "redirect:/blocks?error="+e.getMessage();
        }
    }

    @GetMapping("/blocks/delete/{id}")
    public String deleteBlock(@PathVariable Long id) {

        try {
            service.delete(id);
            return "redirect:/blocks";
        } catch (Exception e){
            return "redirect:/blocks?error="+e.getMessage();
        }
    }
}
