package com.springboot.security_jwt.services.IMPL;

import com.springboot.security_jwt.entities.Role;
import com.springboot.security_jwt.repositories.RoleRepo;
import com.springboot.security_jwt.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceIMPL implements RoleService {

    private final RoleRepo roleRepo;

    public RoleServiceIMPL(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role createNewRole(Role role) {
        return roleRepo.save(role);
    }
}
