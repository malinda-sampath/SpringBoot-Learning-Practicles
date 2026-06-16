package com.springboot.point_of_sale.dto;

public class RegistrationDTO {

    private String email;
    private String password;
    private String role;


    public RegistrationDTO(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public RegistrationDTO(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

