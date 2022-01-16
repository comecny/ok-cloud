package com.example.console.repository;


import com.example.console.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity,Integer> {
}
