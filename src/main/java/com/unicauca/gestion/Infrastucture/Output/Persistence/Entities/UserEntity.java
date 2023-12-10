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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
public class UserEntity {
    @Id
    @Positive(message = "Identification must be positive")
    @NotNull(message = "Identification can't be null" )
    @Size(min = 10, max = 10, message = "Identificaction can't be longer than 10 characters")
    private long idUser;

    @NotNull(message = "Names can't be null" )
    @Size(min = 4, max = 20, message = "Names can't be shorter than 4 characters and longer than 20 characters")
    private String names;

    @NotNull(message = "LastNames can't be null" )
    @Size(min = 2, max = 20, message = "LastNames can't be shorter than 2 characters and longer than 20 characters")
    private String lastNames;

    @Column(unique = true)
    @NotNull(message = "Email can't be null" )
    @Email(message = "email must be valid")
    private String email;

    @Column(unique = true)
    @NotNull(message = "Username can't be null" )
    @Size(min = 4 , max = 10 , message = "Username must be shorter than 4 characters and longer than 10 characters")
    private String username;

    @Size(min = 4 , max = 20 , message = "Username must be shorter than 4 characters and longer than 20 characters")
    @NotNull(message = "Password can't be null" )
    private String password;

    @Pattern(regexp = "[3][0-9]{9}", message = "Number phone must have a '3' at the beginning and total of 10 digits")
    private long numberPhone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_user",
        joinColumns = @JoinColumn(name = "idUser"),
        inverseJoinColumns = @JoinColumn(name = "idRole"))
    private List<RoleEntity> roles;

    public UserEntity(){
        this.roles = new ArrayList<>();
    }
}

