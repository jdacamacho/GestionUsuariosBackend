package com.unicauca.gestion.Infrastucture.Output.Persistence.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
public class UserEntity {
    
    @Id
    private long idUser;
    private String names;
    private String lastNames;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;
    private long numberPhone;

    private String state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_user",
        joinColumns = @JoinColumn(name = "idUser"),
        inverseJoinColumns = @JoinColumn(name = "idRole"))
    private List<RoleEntity> roles;

    public UserEntity(){
        this.roles = new ArrayList<>();
    }
}

