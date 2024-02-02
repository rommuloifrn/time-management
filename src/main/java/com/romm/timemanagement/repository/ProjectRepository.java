package com.romm.timemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.romm.timemanagement.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
