package com.example.spring.security.springsecurity.service;

import com.example.spring.security.springsecurity.model.Role;

import java.util.List;

public interface RoleService {
    public Role findRoleById(Long id);

    public List<Role> getAllRoles();

    List<Role> getRoles();

    public void addRole(Role role);
}
