package com.romm.timemanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.romm.timemanagement.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public boolean requestingUserMatchesDataUser(Long dataSentUserId) {

        String requestingUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        String requestedUsername = repository.findById(dataSentUserId).get().getUsername();
        
        return requestingUsername.equalsIgnoreCase(requestedUsername);
    }   
    
}
