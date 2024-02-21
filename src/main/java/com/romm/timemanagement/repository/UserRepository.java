package com.romm.timemanagement.repository;

import com.romm.timemanagement.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    
    public UserDetails findByLogin(String userName);
}
