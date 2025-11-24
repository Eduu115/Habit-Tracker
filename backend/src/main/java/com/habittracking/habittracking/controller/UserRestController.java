package com.habittracking.habittracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habittracking.habittracking.model.entities.User;
import com.habittracking.habittracking.model.service.UserServiceImplMy8;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="*")
public class UserRestController {

    @Autowired
    UserServiceImplMy8 us;

    @GetMapping("/")
    public List<User> getUsers() {
        return us.findAll();
    }
    
}
