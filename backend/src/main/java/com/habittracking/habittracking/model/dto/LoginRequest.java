package com.habittracking.habittracking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para el login. El campo usernameOrEmail puede contener
 * tanto un username como un email, el sistema lo detectará automáticamente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String usernameOrEmail; // Puede ser username o email
    private String password;
}

