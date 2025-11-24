package com.habittracking.habittracking.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.habittracking.habittracking.model.dto.UserRegisterDto;
import com.habittracking.habittracking.model.entities.User;

@Service
public interface UserService {
    User findById (Long id);
    List<User> findAll();
    User login (String un, String pw);
    User register(User u);
    User toUser(UserRegisterDto urdto);
}
