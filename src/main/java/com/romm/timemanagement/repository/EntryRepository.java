package com.romm.timemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.romm.timemanagement.entities.Entry;
import com.romm.timemanagement.entities.Project;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    public List<Entry> findAllByProject(Project project);
}
