package com.habittracking.habittracking.model.service;

import java.util.List;

import com.habittracking.habittracking.model.dto.UserRegisterDto;
import com.habittracking.habittracking.model.dto.UserUpdateDto;
import com.habittracking.habittracking.model.entities.User;

public interface UserService {
    User findOne(Long id);
    List<User> findAll();
    User login (String un, String pw);
    User register(UserRegisterDto u);
    User update (UserUpdateDto u, Long userId);
    int delete(Long userId);
    User findByUsername(String username); // no la uso al final pero pa poder buscar por username o email 
    User findByEmail(String email);
}
