package com.example.user.persistence.repository;

import com.example.user.persistence.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Usersrepository extends JpaRepository<UsersEntity,Integer> {
}
