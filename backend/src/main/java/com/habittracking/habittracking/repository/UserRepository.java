package com.habittracking.habittracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habittracking.habittracking.model.entities.User;

public interface UserRepository extends JpaRepository<User, Long> { 
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    User findByEmail(String email);
}
