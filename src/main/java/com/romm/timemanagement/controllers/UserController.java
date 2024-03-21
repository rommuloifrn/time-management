package com.romm.timemanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romm.timemanagement.entities.User;
import com.romm.timemanagement.services.UserService;

@RestController @CrossOrigin
@RequestMapping("/users")
public class UserController {
    
    @Autowired UserService service;

    @GetMapping("/count")
    public Long userCount() {
        return service.getUserCount();
    }

    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }
}
