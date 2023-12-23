package com.unicauca.gestion.Infrastucture.Input.ControllerAuth.DTORequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTORequest {
    @NotNull(message = "username cant't be null")
    @NotBlank(message = "username can't be empty")
    private String username;

    @NotNull(message = "password cant't be null")
    @NotBlank(message = "password can't be empty")
    private String password;

    public LoginDTORequest(){

    }

}
