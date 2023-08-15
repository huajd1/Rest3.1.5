package com.example.spring.security.springsecurity.service;

import com.example.spring.security.springsecurity.model.User;

import java.util.List;

public interface UserService {
        List<User> findAll();
        User findOne(Long id);
        User findByName(String username);
        User getUser(Long id);
        void save(User user);
        void update(User user);
        void delete(Long id);



    }

