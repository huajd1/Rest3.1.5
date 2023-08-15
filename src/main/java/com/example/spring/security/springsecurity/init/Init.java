package com.example.spring.security.springsecurity.init;

import com.example.spring.security.springsecurity.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.spring.security.springsecurity.model.User;
import com.example.spring.security.springsecurity.repositories.RoleRepository;
import com.example.spring.security.springsecurity.repositories.UserRepository;


import java.util.HashSet;
import java.util.Set;

@Component
public class Init implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Init(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(String... arg) throws Exception {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
        adminRoles.add(roleAdmin);
        userRoles.add(roleUser);


        // пользователи Admin  и User
        User userAdmin = new User(1l,"Alexander", "GG", 10,"admin", passwordEncoder.encode("admin"), adminRoles);
        User userUser = new User(2l, "Alina", "GG", 25, "user", passwordEncoder.encode("user"), userRoles);
        System.out.println(userAdmin);
        userRepository.save(userAdmin);
        System.out.println(userUser);
        userRepository.save(userUser);
    }
}