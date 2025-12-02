package com.habittracking.habittracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.habittracking.habittracking.model.dto.HabitRegisterDto;
import com.habittracking.habittracking.model.dto.HabitUpdateDto;
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
    public ResponseEntity<?> getHabits(HttpServletRequest request) {
        // obtener la id de usuario del token
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token JWT requerido en el header Authorization");
        }
        String token = authHeader.substring(7);
        // devuelvo los habits del usuario (ya obtenido con el token)
        return ResponseEntity.ok(hs.findByUserId(jwtUtil.extractUserId(token)));
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
    public ResponseEntity<?> createHabit(@RequestBody HabitRegisterDto habitDto, HttpServletRequest request) {
        try {
            // Obtener el token del header Authorization
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token JWT requerido en el header Authorization");
            }
            
            String token = authHeader.substring(7);
            
            // Extraer el userId del token
            Long userId = jwtUtil.extractUserId(token);
            
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token JWT inválido o expirado");
            }
            
            // Crear el hábito
            Habit habit = hs.create(habitDto, userId);
            
            if (habit == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el hábito. Verifique que el usuario existe.");
            }
            
            return ResponseEntity.status(HttpStatus.CREATED).body(habit);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    /**
     * Actualiza un hábito existente.
     * Solo el propietario del hábito puede actualizarlo.
     * 
     * @param id ID del hábito a actualizar
     * @param hudto Datos actualizados del hábito
     * @param request HttpServletRequest para obtener el token JWT
     * @return ResponseEntity con el hábito actualizado o error
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateHabit(@PathVariable long id, @RequestBody HabitUpdateDto hudto, HttpServletRequest request) {
        
        try {
            // Obtener el token del header Authorization
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token JWT requerido en el header Authorization");
            }
            
            String token = authHeader.substring(7);
            
            // Extraer el userId del token
            Long userId = jwtUtil.extractUserId(token);
            
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token JWT inválido o expirado");
            }
            
            // Verificar que el hábito existe
            Habit existingHabit = hs.findById(id);
            if (existingHabit == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hábito no encontrado");
            }
            
            // Verificar que el hábito pertenece al usuario autenticado
            if (!existingHabit.getUser().getId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para actualizar este hábito");
            }
            
            // Actualizar el hábito
            Habit updatedHabit = hs.update(hudto, id);
            
            if (updatedHabit == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el hábito");
            }
            
            return ResponseEntity.ok(updatedHabit);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    /**
     * Elimina un hábito existente.
     * Solo el propietario del hábito puede eliminarlo.
     * 
     * @param id ID del hábito a eliminar
     * @param request HttpServletRequest para obtener el token JWT
     * @return ResponseEntity con mensaje de éxito o error
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHabit(@PathVariable long id, HttpServletRequest request) {
        
        try {
            // Obtener el token del header Authorization
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token JWT requerido en el header Authorization");
            }
            
            String token = authHeader.substring(7);
            
            // Extraer el userId del token
            Long userId = jwtUtil.extractUserId(token);
            
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token JWT inválido o expirado");
            }
            
            // Verificar que el hábito existe
            Habit existingHabit = hs.findById(id);
            if (existingHabit == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hábito no encontrado");
            }
            
            // Verificar que el hábito pertenece al usuario autenticado
            if (!existingHabit.getUser().getId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para eliminar este hábito");
            }
            
            // Eliminar el hábito
            int result = hs.delete(id);
            
            if (result == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hábito no encontrado");
            } else if (result == -1) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el hábito");
            }
            
            return ResponseEntity.ok("Hábito eliminado correctamente");
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }
    
}
