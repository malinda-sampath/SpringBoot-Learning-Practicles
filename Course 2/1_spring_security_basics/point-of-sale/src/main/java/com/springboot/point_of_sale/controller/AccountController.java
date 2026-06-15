package com.springboot.point_of_sale.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin
public class AccountController {

    @GetMapping("/my-account")
    @PreAuthorize("hasRole('ADMIN')")
    //or
    //@Secured("ROLE_ADMIN")
    public String getMyLoans(){
        return "Here are the account details";
    }
}
