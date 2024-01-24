package com.romm.timemanagement.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.romm.timemanagement.entities.Project;
import com.romm.timemanagement.repository.ProjectRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// endpoint!!!!!
@RestController
@RequestMapping(value="/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository repo;

    @GetMapping
    public List<Project> findAll() {
        List<Project> result = repo.findAll();
        return result;
    }
}
