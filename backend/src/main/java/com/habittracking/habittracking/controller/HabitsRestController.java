package com.habittracking.habittracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habittracking.habittracking.model.dto.HabitRegisterDto;
import com.habittracking.habittracking.model.entities.Habit;
import com.habittracking.habittracking.model.service.HabitServiceImplMy8;
import com.habittracking.habittracking.security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/habits")
@CrossOrigin(origins="*")
public class HabitsRestController {
    
    @Autowired
    HabitServiceImplMy8 hs;
    
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/")
    public ResponseEntity<?> getHabits() {
        return ResponseEntity.ok(hs.findAll());
    }
    
    /**
     * Registra un nuevo hábito para el usuario autenticado.
     * El userId se obtiene automáticamente del token JWT en el header Authorization.
     * 
     * @param habitDto Datos del hábito a registrar
     * @param request HttpServletRequest para obtener el token JWT
     * @return ResponseEntity con el hábito creado o error
     */
    @PostMapping("/")
    public ResponseEntity<?> createHabit(
            @RequestBody HabitRegisterDto habitDto,
            HttpServletRequest request) {
        
        try {
            // Obtener el token del header Authorization
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Token JWT requerido en el header Authorization");
            }
            
            String token = authHeader.substring(7);
            
            // Extraer el userId del token
            Long userId = jwtUtil.extractUserId(token);
            
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Token JWT inválido o expirado");
            }
            
            // Crear el hábito
            Habit habit = hs.create(habitDto, userId);
            
            if (habit == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error al crear el hábito. Verifique que el usuario existe.");
            }
            
            return ResponseEntity.status(HttpStatus.CREATED).body(habit);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }
    
}
