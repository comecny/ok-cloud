package com.example.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("auth/testAuth")
    public String testAuth(){
        return "helloAuth";
    }

}
