package com.habittracking.habittracking.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Utilidad para obtener información del usuario autenticado del contexto de seguridad
 */
public class SecurityUtils {

    /**
     * Obtiene el username del usuario autenticado
     */
    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        return null;
    }

    /**
     * Obtiene el ID del usuario autenticado desde el token JWT
     * Nota: Esto requiere acceso al JwtUtil, así que mejor obtenerlo del token directamente
     */
    public static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

