package com.example.user.action;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/testAuth")
    public String testAuth(){
        return "helloAuth";
    }

}
