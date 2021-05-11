package edu.mum.mumsched.entries.controller;

import edu.mum.mumsched.entries.entity.Entry;
import edu.mum.mumsched.entries.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EntryController {

    EntryService service;

    @Autowired
    public EntryController(EntryService service) {
        this.service = service;
    }

    @RequestMapping("/entries")
    public String index(Model model) {
        model.addAttribute("entries",service.getAllEntries());
        return "entries/list";
    }

    @PostMapping("/entries")
    public String addNewEntry(@ModelAttribute Entry entry, Model model) {

        try {
            service.create(entry);
        } catch (Exception e){
            model.addAttribute("errorMessage",e.getMessage());
        }

        model.addAttribute("entries",service.getAllEntries());
        return "entries/list";
    }

    @PostMapping("/entries/edit")
    public String editEntry(@ModelAttribute Entry entry, Model model) {

        try {
            service.edit(entry);
            return "redirect:/entries";
        } catch (Exception e){
            model.addAttribute("errorMessage",e.getMessage());
            return "redirect:/entries?error="+e.getMessage();
        }
    }

    @GetMapping("/entries/delete/{id}")
    public String deleteEntry(@PathVariable Long id) {

        try {
            service.delete(id);
            return "redirect:/entries";
        } catch (Exception e){
            return "redirect:/entries?error="+e.getMessage();
        }
    }
}
