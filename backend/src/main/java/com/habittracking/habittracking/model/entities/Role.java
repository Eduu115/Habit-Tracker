package com.habittracking.habittracking.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode @ToString

@Entity
@Table(name="roles")
public class Role {
    @Id
    public Long id;
    public String name;
    public String description;
}
