package com.habittracking.habittracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.habittracking.habittracking.model.service.RoleServiceImplMy8;



@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/roles")
public class RolesRestController {

    @Autowired
    RoleServiceImplMy8 rs;

    @GetMapping("/")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok(rs.findAll());
    }
    

}
