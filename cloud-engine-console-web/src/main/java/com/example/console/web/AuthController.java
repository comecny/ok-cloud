package com.example.console.web;

import com.example.console.repository.MenuRepository;
import com.example.console.repository.MiddleWareRepository;
import com.example.console.repository.PermissinsRepository;
import com.example.console.repository.RolesRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Resource
    private RolesRepository rolesRepository;

    @Resource
    private PermissinsRepository permissinsRepository;

    @Resource
    private MenuRepository menuRepository;

    @PostMapping("/addRole")
    public void addRole(){
    }

    @PostMapping("/updateRole")
    public void updateRole(){
    }

    @PostMapping("/getRoles")
    public void getRoles(){
    }

    @PostMapping("/addPermission")
    public void addPermission(){
    }

    @PostMapping("/updatPermission")
    public void updatPermission(){
    }

    @PostMapping("/getPermissions")
    public void getPermissions(){
    }

    @PostMapping("/getMenus")
    public void getMenus(){
    }
}
