package com.romm.timemanagement.services;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AuthorizationService authorizationService;

    public Project save(Project project) {
        project.setDateAdded(Instant.now());
        project.setOwner(authorizationService.getUserFromToken());

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
        String username = authorizationService.getTokenUsername();
        User user = (User) userRepo.findByUsername(username);
        return repo.findAllByOwner(user);
    }

    public Long getTotalHours(Long projectId) {
        List<Entry> entries = entryRepo.findAllByProjectId(projectId);
        Long totalHours = (long) 0;
        for (Entry entry : entries) {
            Duration duration = Duration.between(entry.getStart(), entry.getStop());
            totalHours += duration.toHours();    
        }

        return totalHours;
        
    }

    public boolean isNotProjectOwner(Long id) {
        Project project = findProjectById(id);

        Long ownerId = project.getOwner().getId();
        String ownerUsername = userRepo.findById(ownerId).get().getUsername();

        return !authorizationService.getTokenUsername().equals(ownerUsername);
    }
}
