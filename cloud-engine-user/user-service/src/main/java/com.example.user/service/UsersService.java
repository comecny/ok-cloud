package com.example.user.service;

import com.example.user.persistence.entity.UsersEntity;
import com.example.user.persistence.repository.Usersrepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private Usersrepository usersrepository;

    public UsersService(Usersrepository usersrepository) {
        this.usersrepository = usersrepository;
    }

    public List<UsersEntity> getUsers(){
        return usersrepository.findAll();
    }
}
