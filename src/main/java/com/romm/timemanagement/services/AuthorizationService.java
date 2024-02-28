package com.romm.timemanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.romm.timemanagement.entities.User;
import com.romm.timemanagement.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public User getUserFromToken() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return (User) repository.findByUsername(username);
    }

    public String getTokenUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    
}
