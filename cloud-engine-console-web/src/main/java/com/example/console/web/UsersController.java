package com.example.console.web;

import com.example.console.repository.UsersRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/users")
@RestController
public class UsersController {

    @Resource
    private UsersRepository usersRepository;

    @PostMapping("/login")
    public void login(){
    }

    @PostMapping("/layout")
    public void layout(){
    }

    @PostMapping("/addUser")
    public void addUser(){
    }

    @PostMapping("/updateUser")
    public void updateUser(){
    }

    @PostMapping("/getUsers")
    public void getUsers(){
    }
}
