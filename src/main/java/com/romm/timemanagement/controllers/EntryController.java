package com.romm.timemanagement.controllers;

import java.util.List;

import com.romm.timemanagement.entities.Entry;
import com.romm.timemanagement.services.EntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/entries")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @GetMapping()
    public List<Entry> findAll() {
        return entryService.findAll();
    }

    @PostMapping()
    public Entry StartEntry(@RequestBody Entry entry) {
        return entryService.startEntry(entry);
    }
}
