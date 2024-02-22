package com.romm.timemanagement.services;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romm.timemanagement.entities.Entry;
import com.romm.timemanagement.entities.Project;
import com.romm.timemanagement.repository.EntryRepository;
import com.romm.timemanagement.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repo;

    @Autowired
    private EntryRepository entryRepo;

    public Project save(Project project) {
        project.setDateAdded(Instant.now());
        return repo.save(project);
    }

    public Project findProjectById(Long id) {
        return repo.findById(id).get();
    }

    public Project editProjectById(Long id, Project newProject) {
        Project project = repo.findById(id).get();

        if (Objects.nonNull(newProject.getTitle()) && !"".equalsIgnoreCase(newProject.getTitle()))
            project.setTitle(newProject.getTitle());

        if (Objects.nonNull(newProject.getDescription()) && !"".equalsIgnoreCase(newProject.getDescription()))
            project.setDescription(newProject.getDescription());

        return repo.save(project);
    }

    public void deleteById(Long projectId) {
        repo.deleteById(projectId);
    }

    public List<Project> findAll() {
        return repo.findAll();
    }

    public Long getTotalHours(Long projectId) {
        Project project = repo.findById(projectId).get();
        List<Entry> entries = entryRepo.findAllByProject(project);
        Long totalHours = (long) 0;
        for (Entry entry : entries) {
            Duration duration = Duration.between(entry.getStart(), entry.getStop());
            totalHours += duration.toHours();    
        }

        return totalHours;
        
    }
}
