package com.romm.timemanagement.services;
import com.romm.timemanagement.entities.Entry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romm.timemanagement.repository.EntryRepository;

@Service
public class EntryService {
    
    @Autowired
    private EntryRepository entryRepo;
    
    public Entry startEntry(Entry entry) {
        return entryRepo.saveAndFlush(entry);
    }

    public List<Entry> findAll() {
        return entryRepo.findAll();
    }


}
