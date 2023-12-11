package com.unicauca.gestion.Infrastucture.Input.UserDTO.DTORequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTORequest {

    @Positive(message = "idRole must be positive")
    private long idRole;

    @NotNull(message = "Role name can't be null")
    @NotBlank(message = "Role name can't be empty")
    private String name;

    public RoleDTORequest(){

    }
}
