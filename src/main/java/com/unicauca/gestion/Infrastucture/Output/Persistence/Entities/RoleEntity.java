package com.unicauca.gestion.Infrastucture.Output.Persistence.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "roles")
@Data
@AllArgsConstructor
public class RoleEntity {
    @Id
    private long idRole;
    private String name;

    public RoleEntity(){

    }
}
