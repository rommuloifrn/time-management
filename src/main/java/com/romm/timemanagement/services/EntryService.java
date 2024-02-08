package com.romm.timemanagement.services;
import com.romm.timemanagement.entities.Entry;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romm.timemanagement.repository.EntryRepository;

@Service
public class EntryService {
    
    @Autowired
    private EntryRepository entryRepo;
    
    public Entry startEntry(Entry entry) {
        entry.setStart(Instant.now());
        return entryRepo.save(entry);
    }

    public Entry endEntry(Long id) {
        Entry entry = entryRepo.findById(id).get();

        entry.setStop(Instant.now());
        return entryRepo.save(entry);
    }

    public Entry saveEntry(Entry entry) {
        return entryRepo.save(entry);
    }

    public Entry findEntryById(Long id) {
        return entryRepo.findById(id).get();
    }

    public Entry editEntry(Long id, Entry newEntry) {
        Entry entry = entryRepo.findById(id).get();

        if (Objects.nonNull(newEntry.getTitle()) && !"".equalsIgnoreCase(newEntry.getTitle()))
            entry.setTitle(newEntry.getTitle());

        if (Objects.nonNull(newEntry.getText()) && !"".equalsIgnoreCase(newEntry.getText()))
            entry.setText(newEntry.getText());

        if ( Objects.nonNull(newEntry.getStart()) )
            entry.setStart(newEntry.getStart());

        if ( Objects.nonNull(newEntry.getStop()) )
            entry.setStop(newEntry.getStop());

        return entryRepo.save(entry);
    }

    public void deleteEntryById(Long id) {
        entryRepo.deleteById(id);
    }

    public List<Entry> findAll() {
        return entryRepo.findAll();
    }
}
