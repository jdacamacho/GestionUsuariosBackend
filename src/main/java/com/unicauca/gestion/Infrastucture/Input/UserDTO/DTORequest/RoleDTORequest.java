package com.unicauca.gestion.Infrastucture.Input.UserDTO.DTORequest;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTORequest {
    @NotNull(message = "Id role can't be null")
    private long idRole;

    @NotNull(message = "Name can't be null")
    private String name;

    public RoleDTORequest(){

    }
}
