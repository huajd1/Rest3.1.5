package com.example.spring.security.springsecurity.controllers;

import com.example.spring.security.springsecurity.model.User;
import com.example.spring.security.springsecurity.service.RoleServiceImp;
import com.example.spring.security.springsecurity.service.UserServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImp userServiceImp;


    public AdminController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    
    }

    @GetMapping
    public String adminView(Model model, Principal principal) {
        User princ = userServiceImp.findByName(principal.getName());
        model.addAttribute("princ", princ);
        return "admin";
    }

}
