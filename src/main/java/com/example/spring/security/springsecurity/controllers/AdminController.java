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
    private final RoleServiceImp roleServiceImp;

    public AdminController(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping
    public String adminView(Model model, Principal principal) {
        model.addAttribute("allUsers", userServiceImp.findAll());
        User princ = userServiceImp.findByName(principal.getName());
        model.addAttribute("princ", princ);
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleServiceImp.getRoles());
        model.addAttribute("titleTable", "Список всех пользователей:");
        return "admin";
    }

}