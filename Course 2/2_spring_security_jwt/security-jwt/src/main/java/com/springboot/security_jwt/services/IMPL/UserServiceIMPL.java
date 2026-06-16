package com.springboot.security_jwt.services.IMPL;

import com.springboot.security_jwt.entities.Role;
import com.springboot.security_jwt.entities.User;
import com.springboot.security_jwt.repositories.RoleRepo;
import com.springboot.security_jwt.repositories.UserRepo;
import com.springboot.security_jwt.services.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceIMPL implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public UserServiceIMPL(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public User registerUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public void initRoleAndUser() {

        if (!roleRepo.existsById("ADMIN")) {
            Set<Role> adminRoleSet = new HashSet<>();
            Role adminRole = new Role(
                    "ADMIN",
                    "This is admin role"
            );

            roleRepo.save(adminRole);
            adminRoleSet.add(adminRole);

            if (!userRepo.existsById("admin123")) {

                User adminUser = new User(
                        "admin123",
                        "malinda",
                        "sampath",
                        "12345",
                        adminRoleSet
                );

                userRepo.save(adminUser);
            }
        }

        if (!roleRepo.existsById("USER")) {
            Set<Role> userRoleSet = new HashSet<>();
            Role userRole = new Role(
                    "USER",
                    "This is user role"
            );

            roleRepo.save(userRole);
            userRoleSet.add(userRole);

            if (!userRepo.existsById("user123")) {

                User user = new User(
                        "user123",
                        "nipuni",
                        "anushika",
                        "12345",
                        userRoleSet
                );

                userRepo.save(user);
            }
        }
    }
}
