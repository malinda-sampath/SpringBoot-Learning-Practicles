package com.springboot.security_jwt.services;

import com.springboot.security_jwt.entities.User;

public interface UserService {
    User registerUser(User user);

    void initRoleAndUser();
}
