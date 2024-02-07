package com.romm.timemanagement.services;
import com.romm.timemanagement.entities.Entry;

import java.time.Instant;
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

    public Entry endEntry(Long id) {
        Entry entry = entryRepo.findById(id).get();
        entry.setStop(Instant.now());
        return entryRepo.save(entry);
    }

    public List<Entry> findAll() {
        return entryRepo.findAll();
    }

    public void deleteEntryById(Long id) {
        entryRepo.deleteById(id);
    }

    public Entry editEntry(Long id, Entry newData) {
        Entry entry = entryRepo.findById(id).get();

        // not verifying for blank or equal fields
        entry.setTitle(newData.getTitle());
        entry.setText(newData.getText());
        entry.setStart(newData.getStart());
        entry.setStop(newData.getStop());

        return entryRepo.save(entry);
    }


}
