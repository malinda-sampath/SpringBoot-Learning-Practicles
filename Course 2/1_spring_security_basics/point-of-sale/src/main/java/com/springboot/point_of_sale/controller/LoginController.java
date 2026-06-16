package com.springboot.point_of_sale.controller;

import com.springboot.point_of_sale.dto.RegistrationDTO;
import com.springboot.point_of_sale.entities.User;
import com.springboot.point_of_sale.repo.UserRepo;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class LoginController {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public LoginController(BCryptPasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationDTO registrationDTO){
        ResponseEntity<String> response = null;
        try {
            User user = new User();
            user.setEmail(registrationDTO.getEmail());
            user.setRole(registrationDTO.getRole());
            String hashPassword = passwordEncoder.encode(registrationDTO.getPassword());
            user.setPassword(hashPassword);
            User savedUser = userRepo.save(user);

            if (savedUser.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("User saved successfully");
            }
        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred due to: " + e.getMessage());
        }

        return response;
    }
}
