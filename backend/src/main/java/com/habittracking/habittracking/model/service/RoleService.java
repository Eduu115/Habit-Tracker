package com.habittracking.habittracking.model.service;

import java.util.List;

import com.habittracking.habittracking.model.entities.Role;

public interface RoleService {
    Role findById(Long id);
    List<Role> findAll();
}
