package com.example.spring.security.springsecurity.controllers;

import com.example.spring.security.springsecurity.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("user")
public class UserController {
    @GetMapping
    public String index(Model model, @AuthenticationPrincipal User curUser) {
        model.addAttribute("curUser", curUser);
        return "user";
    }
}

