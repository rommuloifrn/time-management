package com.romm.timemanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romm.timemanagement.entities.User;
import com.romm.timemanagement.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public Long getUserCount() {
        return repository.count();
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).get();
    }
}
