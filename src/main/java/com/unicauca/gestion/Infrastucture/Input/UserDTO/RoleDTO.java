package com.unicauca.gestion.Infrastucture.Input.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTO {
    private long idRole;
    private String name;

    public RoleDTO(){

    }
}
