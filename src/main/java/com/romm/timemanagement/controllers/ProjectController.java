package com.romm.timemanagement.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.romm.timemanagement.entities.Project;
import com.romm.timemanagement.services.ProjectService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

// endpoint!!!!!
@RestController
@RequestMapping(value="/projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @GetMapping
    public List<Project> findAll() {
        List<Project> result = service.findAll();
        return result;
    }

    @GetMapping("/project/{id}")
    public Project findProjectById(@PathVariable("id") Long id) {
        return service.findProjectById(id);
    }

    @PostMapping()
    public Project saveProject(@RequestBody Project project) {
        return service.save(project);
    }

    @PutMapping("/projects/{id}")
    public Project editProjectById(@PathVariable("id") Long id, @RequestBody Project project) {
        return service.editProjectById(id, project);
    }

    @DeleteMapping("/projects/{id}")
    public void deleteById(@PathVariable("id") Long projectId) {
        service.deleteById(projectId);
    }
}
