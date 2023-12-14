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
    private String state;
    private List<Role> roles;

    public User(){
        this.roles = new ArrayList<>();
    }

    public boolean stateIsValid(){
        if(!this.state.equals("Habilitado") && !this.state.equals("Inhabilitado")){
            return false;
        }
        return true;
    }

    public boolean isValidRole(List<Role> validRoles){
        List<Role> roles = this.getRoles();
        int wasFound = 0;
        for (Role role : roles) {
            for (Role roleValid : validRoles) {
                if(role.equals(roleValid)) wasFound ++;
            }
        }
        if(roles.size() == wasFound) return true;
        return false;
    }

    
}

