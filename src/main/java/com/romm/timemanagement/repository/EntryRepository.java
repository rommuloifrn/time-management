package com.romm.timemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.romm.timemanagement.entities.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    
}
