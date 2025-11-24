package com.habittracking.habittracking.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habittracking.habittracking.model.entities.Role;
import com.habittracking.habittracking.repository.RoleRepository;

@Service
public class RoleServiceImplMy8 implements RoleService{

    @Autowired
    RoleRepository rrepo;

    @Override
    public Role findById(Long id) {
        return rrepo.findById(id).orElse(null);
    }

    @Override
    public List<Role> findAll() {
        return rrepo.findAll();
    }
    
}
