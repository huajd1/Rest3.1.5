package com.example.spring.security.springsecurity.controllers;

import com.example.spring.security.springsecurity.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("admin")
public class AdminController {

    @GetMapping
    public String admin(Model model, @AuthenticationPrincipal User curUser) {
        model.addAttribute("curUser", curUser);
        return "admin";
    }
}