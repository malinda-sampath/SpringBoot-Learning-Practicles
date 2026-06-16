package com.springboot.security_jwt.controllers;

import com.springboot.security_jwt.entities.Role;
import com.springboot.security_jwt.services.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public Role createNewRole(@RequestBody Role role){
        return roleService.createNewRole(role);
    }
}
