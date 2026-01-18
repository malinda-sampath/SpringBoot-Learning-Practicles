package com.springboot.point_of_sale.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1/test")
public class TestController {

    @GetMapping(path="/my-text")
    public String getMyText(){
        return "Hello World";
    }

    @GetMapping(path="/my-text1")
    public String getMyText1(){
        return "Hello World";
    }
}
