package com.romm.timemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.romm.timemanagement.entities.Project;
import com.romm.timemanagement.entities.User;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    public List<Project> findAllByOwner(User owner);
}
