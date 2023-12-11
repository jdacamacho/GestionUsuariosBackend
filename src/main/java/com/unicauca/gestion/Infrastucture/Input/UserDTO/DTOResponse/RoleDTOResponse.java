package com.unicauca.gestion.Infrastucture.Input.UserDTO.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTOResponse {
    private long idRole;
    private String name;

    public RoleDTOResponse(){

    }
}
