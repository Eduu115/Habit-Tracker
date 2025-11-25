package com.habittracking.habittracking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode @ToString

public class UserUpdateDto {
    // Que queremos que pueda actualizar el usuario ?? - las cosas que no sean importantes ni claves de nada
    private String username; // no pasa nada porque se logea con el correo
    private String name;
    private String lastName;
}
