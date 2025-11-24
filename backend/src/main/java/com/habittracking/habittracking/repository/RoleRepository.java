package com.habittracking.habittracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habittracking.habittracking.model.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
