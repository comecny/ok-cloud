package com.example.user.controller;

import com.redis.util.RedisKey;
import com.redis.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class TestController {

    @Autowired
    private RedisUtil redisUtil;

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/testUser")
    public String testUser(){
        logger.info("进入");
        redisUtil.save(RedisKey.HOT_KEY,"dwqdq",10000L);
        return "hellouser";
    }
        
}
