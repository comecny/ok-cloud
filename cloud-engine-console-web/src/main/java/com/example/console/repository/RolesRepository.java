package com.example.console.repository;

import com.example.console.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<RolesEntity,Integer> {
}
