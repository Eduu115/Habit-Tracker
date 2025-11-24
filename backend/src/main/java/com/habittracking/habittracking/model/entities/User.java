package com.habittracking.habittracking.model.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString

@Entity
@Table(name="usuarios")
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    @Column(name="last_name")
    private String lastName;
    private int enabled;
    @Column(name="fecha_creacion")
    private LocalDate fechaCreacion;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

}
