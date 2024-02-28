package com.romm.timemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romm.timemanagement.entities.Entry;
import com.romm.timemanagement.services.EntryService;
import com.romm.timemanagement.services.ProjectService;

@RestController
@RequestMapping(value="/entries")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private ProjectService projectService;

    @PostMapping() // start
    public ResponseEntity<?> startEntry(@RequestBody Entry entry) {
        if (projectService.isNotProjectOwner(entry.getProject().getId())) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not own the project");

        return ResponseEntity.ok(entryService.startEntry(entry));
    }

    @PutMapping("/end/{id}") // end
    public ResponseEntity<?> endEntry(@PathVariable("id") Long id) {
        if (projectService.isNotProjectOwner(entryService.findEntryById(id).getProject().getId())) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not own the project");

        return ResponseEntity.ok(entryService.endEntry(id));
    }

    @DeleteMapping("/{id}") // delete
    public ResponseEntity<?> DeleteEntryById(@PathVariable("id") Long id) {
        if (projectService.isNotProjectOwner(entryService.findEntryById(id).getProject().getId())) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not own the project");

        entryService.deleteEntryById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}") // update
    public ResponseEntity<?> editEntry(@PathVariable("id") Long id, @RequestBody Entry entry) {
        if (projectService.isNotProjectOwner(entryService.findEntryById(id).getProject().getId())) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not own the project");

        return ResponseEntity.ok(entryService.editEntry(id, entry));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAllByProject(@PathVariable("id") Long id) {
        if (projectService.isNotProjectOwner(entryService.findEntryById(id).getProject().getId())) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not own the project");

        return ResponseEntity.ok(entryService.findAllByProjectId(id));
    }

}
