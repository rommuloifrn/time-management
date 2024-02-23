package com.romm.timemanagement.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception { // conjunto dos filtros de segurança. São aplicados a todas requisições http!
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                
                .requestMatchers(HttpMethod.POST, "/projects").permitAll()                 // create project
                .requestMatchers(HttpMethod.GET, "/projects").hasRole("ADMIN")        // get all projects
                .requestMatchers(HttpMethod.GET, "/projects/gethours/{id}").permitAll()    // get project total hours from entries
                .requestMatchers(HttpMethod.GET, "/projects/mine").hasRole("USER")    // get the current user projects

                .requestMatchers(HttpMethod.POST, "/entries").hasRole("USER")         // start entry
                .requestMatchers(HttpMethod.GET, "/entries/end/{id}").hasRole("USER") // end entry
                .requestMatchers(HttpMethod.GET, "/entries").hasRole("USER")          // get all entries
                
                .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")           // get total user quantity
                .requestMatchers(HttpMethod.GET, "/users/count").hasRole("ADMIN")     // get total user quantity

                .anyRequest().authenticated()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { // provê o método authenticate()
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { // criptografa/descriptografa senhas
        return new BCryptPasswordEncoder();
    }
}
