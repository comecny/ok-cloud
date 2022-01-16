package com.example.console.repository;

import com.example.console.entity.PermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissinsRepository extends JpaRepository<PermissionsEntity,Integer> {
}
