package com.habittracking.habittracking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode

public class HabitRegisterDto {

    private String nombre;
    private String descripcion;
    private String frecuencia;
}
