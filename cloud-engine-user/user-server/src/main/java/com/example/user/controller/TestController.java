package com.example.user.controller;

import com.example.api.service.auth.TestAuthService;

import com.example.remote.RemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final RemoteService remoteService;

    public TestController(RemoteService remoteService) {
        this.remoteService = remoteService;
    }

    @GetMapping("/testUser")
    public String testUser(){
        TestAuthService service = remoteService.instanceService(TestAuthService.class);
        String s = service.testAuth();
        logger.info("进入"+s);
        return "hellouser";
    }
        
}
