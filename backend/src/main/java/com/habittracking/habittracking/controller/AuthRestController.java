package com.habittracking.habittracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habittracking.habittracking.model.dto.JwtResponse;
import com.habittracking.habittracking.model.dto.LoginRequest;
import com.habittracking.habittracking.model.entities.User;
import com.habittracking.habittracking.model.service.UserService;
import com.habittracking.habittracking.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService us;

    /**
     * Endpoint de login que genera un token JWT.
     * Acepta tanto username como email para autenticarse.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Autenticar con Spring Security (busca automáticamente por username o email)
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsernameOrEmail(),
                    loginRequest.getPassword()
                )
            );
            // si llega aqui, la autenticacion fue exitosa
        } catch (BadCredentialsException e) {
            // si llega aqui, la autenticacion fue fallida
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        // Obtener el usuario completo (buscar por username o email)
        User user = us.findByUsername(loginRequest.getUsernameOrEmail());
        if (user == null) {
            user = us.findByEmail(loginRequest.getUsernameOrEmail());
        }

        if (user == null) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }

        // Generar JWT usando el username como subject (para consistencia con Spring Security)
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());

        // Devolver token con información del usuario
        return ResponseEntity.ok(new JwtResponse(token, "Bearer", user.getId(), user.getUsername(), user.getEmail()));
    }
}

