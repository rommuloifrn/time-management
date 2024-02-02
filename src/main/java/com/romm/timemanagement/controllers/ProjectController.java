package com.romm.timemanagement.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.romm.timemanagement.entities.Project;
import com.romm.timemanagement.repository.ProjectRepository;
import com.romm.timemanagement.services.ProjectService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping()
    public Project saveProject(@RequestBody Project project) {
        return service.save(project);
    }
}
