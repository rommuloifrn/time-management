package com.romm.timemanagement.services;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.romm.timemanagement.entities.Entry;
import com.romm.timemanagement.entities.Project;
import com.romm.timemanagement.entities.User;
import com.romm.timemanagement.repository.EntryRepository;
import com.romm.timemanagement.repository.ProjectRepository;
import com.romm.timemanagement.repository.UserRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repo;

    @Autowired
    private UserRepository userRepo;

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

    public List<Project> getUserProjects() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = (User) userRepo.findByUsername(username);
        return repo.findAllByOwner(user);
        
    }
}
