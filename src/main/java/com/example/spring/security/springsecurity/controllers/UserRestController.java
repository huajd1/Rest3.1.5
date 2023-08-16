package com.example.spring.security.springsecurity.controllers;

import com.example.spring.security.springsecurity.model.User;
import com.example.spring.security.springsecurity.service.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserServiceImp userServiceImp;

    public UserRestController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }


    @GetMapping
    public ResponseEntity<User> getUser(Authentication authentication) {
        String username = authentication.getName();
        User user = userServiceImp.findByName(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}