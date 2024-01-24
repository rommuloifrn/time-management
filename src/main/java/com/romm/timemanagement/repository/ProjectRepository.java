package com.romm.timemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romm.timemanagement.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
