package com.romm.timemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romm.timemanagement.DTO.AuthenticationDTO;
import com.romm.timemanagement.DTO.RegisterDTO;
import com.romm.timemanagement.repository.UserRepository;
import com.romm.timemanagement.services.TokenService;
import com.romm.timemanagement.entities.User;

import jakarta.validation.Valid;

@RestController @CrossOrigin
@RequestMapping("auth") 
public class AuthenticationController {

    @Autowired private AuthenticationManager authenticationManager; // gerenciador de autenticação (bean definido em SecurityConfigurations)

    @Autowired private UserRepository repository;

    @Autowired private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword); // .authenticate recebe um usernamePassword (token) criado na linha acima

        var token = tokenService.generateToken( (User) auth.getPrincipal());

        return ResponseEntity.ok(token); // nao estou usando um DTO aqui como a kipper usa
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByUsername(data.username()) !=null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.username(), encryptedPassword, data.role(), data.email());
        this.repository.save(user);

        return ResponseEntity.ok().build();
    }
}
