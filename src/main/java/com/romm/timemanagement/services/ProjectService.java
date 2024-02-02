package com.romm.timemanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romm.timemanagement.entities.Project;
import com.romm.timemanagement.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repo;

    public Project save(Project project) {
        return repo.saveAndFlush(project);
    }

    public List<Project> findAll() {
        return repo.findAll();
    }
}
