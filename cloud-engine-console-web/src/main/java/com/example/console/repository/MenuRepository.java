package com.example.console.repository;

import com.example.console.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity,Integer> {
}
