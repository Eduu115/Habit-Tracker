package com.habittracking.habittracking.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habittracking.habittracking.model.dto.UserRegisterDto;
import com.habittracking.habittracking.model.entities.User;
import com.habittracking.habittracking.repository.UserRepository;

@Service
public class UserServiceImplMy8 implements UserService{

    @Autowired
    UserRepository urepo;
    @Autowired
    RoleServiceImplMy8 rs;

    // ------------------ HERENCIA ---------------------
    @Override
    public User findOne(Long id) {
        return urepo.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return urepo.findAll();
    }

    @Override
    public User login(String un, String pw) {
        return urepo.findfindByUsernameAndPassword(un, pw);
    }

    @Override
    public User register(UserRegisterDto u) {
         
        try {
            u.setPassword(withNoop(u.getPassword())); // le ponemos el noop
            return urepo.save(toUser(u));
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    // ----------------------- FUNCIONALIDADES PROPIAS -----------------------
    public User toUser(UserRegisterDto urdto) {
        User finalUser = new User();
        finalUser.setPassword(urdto.getPassword());
        finalUser.setEmail(urdto.getEmail());
        finalUser.setEnabled(1);
        finalUser.setFechaCreacion(LocalDate.now());
        finalUser.setLastName(urdto.getLastName());
        finalUser.setRole(rs.findById(1L));
        finalUser.setUsername(urdto.getUsername());
        return finalUser;
    }
    
    private String withNoop(String raw) {
        if (raw.startsWith("{noop}")) {
            return raw;
        }
        String conNoop = "{noop}" + raw;
        return conNoop;
    }


}
