package com.example.spring.security.springsecurity.controllers;

import com.example.spring.security.springsecurity.model.User;
import com.example.spring.security.springsecurity.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestAPIController {
    private final UserServiceImp userServiceImp;

    @Autowired
    public RestAPIController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> showAllUsers() {

        return new ResponseEntity<>(userServiceImp.findAll(), HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userServiceImp.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody User user) {
        userServiceImp.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user, @PathVariable Long id) {
        userServiceImp.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userServiceImp.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
