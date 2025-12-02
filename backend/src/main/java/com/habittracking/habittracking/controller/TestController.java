package com.habittracking.habittracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador temporal para generar hash BCrypt válido
 * ELIMINAR EN PRODUCCIÓN
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Endpoint temporal para generar hash BCrypt de "password123"
     * ELIMINAR EN PRODUCCIÓN
     */
    @GetMapping("/generate-hash")
    public ResponseEntity<?> generateHash() {
        String password = "password123";
        String hash = passwordEncoder.encode(password);
        return ResponseEntity.ok("Hash BCrypt para '" + password + "':\n" + hash);
    }
}

