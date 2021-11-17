package com.example.user.controller;

import com.example.remote.record.annotation.RPCAsk;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RPCAsk
    @GetMapping("/testAuth")
    public String testAuth(){
        return "helloAuth";
    }

}
