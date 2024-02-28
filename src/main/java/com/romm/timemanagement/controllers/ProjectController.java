package com.romm.timemanagement.controllers;

import java.util.List;

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

import com.romm.timemanagement.entities.Project;
import com.romm.timemanagement.services.ProjectService;

// endpoint!!!!!
@RestController
@RequestMapping(value="/projects")
public class ProjectController {

    @Autowired
    private ProjectService service;


    @PostMapping() // create
    public ResponseEntity<?> saveProject(@RequestBody Project project) {
        return ResponseEntity.ok(service.save(project));
    }

    @PutMapping("/{id}") // update (acho q ta meio burro a disposiçao dessas variaveis - id n ja vem em project?)
    public ResponseEntity<?> editProjectById(@PathVariable("id") Long id, @RequestBody Project project) {
        if (service.isNotProjectOwner(id)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not own the project");
        
        return ResponseEntity.ok(service.editProjectById(id, project));
    }

    @GetMapping("/{id}") // read
    public ResponseEntity<?> findProjectById(@PathVariable("id") Long id) {
        if (service.isNotProjectOwner(id)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not own the project");

        return ResponseEntity.ok(service.findProjectById(id));
    }

    @DeleteMapping("/{id}") // delete
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        if (service.isNotProjectOwner(id)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not own the project");
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Project> findAll() {
        return service.findAll();
    }

    @GetMapping("/gethours/{id}") // get hours
    public ResponseEntity<?> getProjectHours(@PathVariable("id") Long id) {
        if (service.isNotProjectOwner(id)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not own the project");

        return ResponseEntity.ok(service.getTotalHours(id));
    }
    
}
