package com.habittracking.habittracking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.habittracking.habittracking.model.entities.User;
import com.habittracking.habittracking.model.service.UserService;

/**
 * Servicio personalizado para cargar usuarios en Spring Security
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar por username o email
        User user = userService.findByUsername(username);
        if (user == null) {
            user = userService.findByEmail(username);
        }
        
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .disabled(user.getEnabled() == 0)
                .authorities("ROLE_USER") // Puedes expandir esto según tus roles
                .build();
    }
}

