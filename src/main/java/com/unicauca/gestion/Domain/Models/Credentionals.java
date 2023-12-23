package com.unicauca.gestion.Domain.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Credentionals {
    private long idUser;
    private String username;
    private String email;
    private List<String> access;
    private String token;

    public Credentionals(){

    }

}
