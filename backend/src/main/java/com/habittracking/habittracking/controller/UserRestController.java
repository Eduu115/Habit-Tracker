package com.habittracking.habittracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habittracking.habittracking.model.dto.UserRegisterDto;
import com.habittracking.habittracking.model.dto.UserUpdateDto;
import com.habittracking.habittracking.model.service.UserServiceImplMy8;



@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="*")
public class UserRestController {

    @Autowired
    UserServiceImplMy8 us;

    @GetMapping("/")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(us.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id) {
        return ResponseEntity.ok(us.findOne(id));
    }
    
    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody UserRegisterDto uRDto) {
        return ResponseEntity.ok(us.register(uRDto));
    }
    // EN EL PUT Y POST Y TOdO A LO QUE SE LE PASA ID NO FUNCIONA !!! RESOLVER !!!
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody UserUpdateDto uUDto) {
        return ResponseEntity.ok(us.update(uUDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long userId){
        return ResponseEntity.ok(us.delete(userId));
    }

}
