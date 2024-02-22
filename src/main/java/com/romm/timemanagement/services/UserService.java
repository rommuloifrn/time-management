package com.romm.timemanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romm.timemanagement.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public Long getUserCount() {
        return repository.count();
    }
}
