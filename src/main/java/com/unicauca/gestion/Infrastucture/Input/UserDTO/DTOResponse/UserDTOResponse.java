package com.unicauca.gestion.Infrastucture.Input.UserDTO.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDTOResponse {
    
    private long idUser;
    private String names;
    private String lastNames;
    private String email;
    private String username;
    private String password;
    private long numberPhone;
    private String state;
    private List<RoleDTOResponse> roles;

    public UserDTOResponse(){
        this.roles = new ArrayList<>();
    }
}

