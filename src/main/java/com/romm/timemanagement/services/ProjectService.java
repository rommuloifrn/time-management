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

    public List<Project> findAll() {
        return repo.findAll();
    }

    public Project findProjectById(Long id) {
        return repo.findById(id).get();
    }

    public Project save(Project project) {
        return repo.saveAndFlush(project);
    }

    public Project editProjectById(Long id, Project newData) {
        Project project = repo.findById(id).get();

        // not verifying for blank or equal fields 
        project.setTitle(newData.getTitle());
        project.setDescription(newData.getDescription());

        return repo.save(project);
    }

    public void deleteById(Long projectId) {
        repo.deleteById(projectId);
    }
}
