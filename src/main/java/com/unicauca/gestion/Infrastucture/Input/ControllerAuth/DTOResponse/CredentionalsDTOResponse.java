package com.unicauca.gestion.Infrastucture.Input.ControllerAuth.DTOResponse;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentionalsDTOResponse {
    private long idUser;
    private String username;
    private String email;
    private List<String> access;
    private String token;

    public CredentionalsDTOResponse(){
        this.access = new ArrayList<>();
    }
}
