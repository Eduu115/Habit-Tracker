package com.habittracking.habittracking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode

public class UserRegisterDto {
    
    private String username;
    private String email;
    private String password;
    private String name;
    private String lastName;

}
