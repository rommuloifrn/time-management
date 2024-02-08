package com.romm.timemanagement.controllers;

import java.util.List;

import com.romm.timemanagement.entities.Entry;
import com.romm.timemanagement.services.EntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/entries")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @PostMapping() // start
    public Entry startEntry(@RequestBody Entry entry) {
        return entryService.startEntry(entry);
    }

    @PutMapping("/end/{id}") // end
    public Entry endEntry(@PathVariable("id") Long id) {
        return entryService.endEntry(id);
    }

    @DeleteMapping("/{id}") // delete
    public void DeleteEntryById(@PathVariable("id") Long id) {
        entryService.deleteEntryById(id);
    }

    @PutMapping("/{id}") // update
    public Entry editEntry(@PathVariable("id") Long id, @RequestBody Entry entry) {
        return entryService.editEntry(id, entry);
    }

    @GetMapping()
    public List<Entry> findAll() {
        return entryService.findAll();
    }

}
