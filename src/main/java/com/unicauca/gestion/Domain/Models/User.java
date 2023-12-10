package com.unicauca.gestion.Domain.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private long idUser;
    private String names;
    private String lastNames;
    private String email;
    private String username;
    private String password;
    private long numberPhone;
    private List<Role> roles;

    public User(){
        this.roles = new ArrayList<>();
    }
}

