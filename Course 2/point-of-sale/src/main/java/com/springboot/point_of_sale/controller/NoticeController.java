package com.springboot.point_of_sale.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notice")
@CrossOrigin
public class NoticeController {

    @GetMapping("/my-notices")
    public String getAccountDetails(){
        return "Here are the notice details";
    }
}
