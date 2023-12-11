package com.unicauca.gestion.Infrastucture.Input.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UserDTO {

    @Positive(message = "Identification must be positive")
    @NotNull(message = "Identification can't be null" )
    @Digits(integer = 10, fraction = 0, message = "Identificaction can't be longer than 10 characters")
    private long idUser;

    @NotNull(message = "Names can't be null" )
    @Size(min = 4, max = 20, message = "Names can't be shorter than 4 characters and longer than 20 characters")
    private String names;

    @NotNull(message = "LastNames can't be null" )
    @Size(min = 2, max = 20, message = "LastNames can't be shorter than 2 characters and longer than 20 characters")
    private String lastNames;

    @NotNull(message = "Email can't be null" )
    @Email(message = "email must be valid")
    private String email;

    @NotNull(message = "Username can't be null" )
    @Size(min = 4 , max = 10 , message = "Username must be shorter than 4 characters and longer than 10 characters")
    private String username;

    @Size(min = 4 , max = 20 , message = "Username must be shorter than 4 characters and longer than 20 characters")
    @NotNull(message = "Password can't be null" )
    private String password;

    @Min(value = 3000000000L, message = "Number phone must have a '3' at the beginning and a total of 10 digits")
    @Max(value = 3999999999L, message = "Number phone must have a '3' at the beginning and a total of 10 digits")
    private long numberPhone;
    
    private String state;
    private List<RoleDTO> roles;

    public UserDTO(){
        this.roles = new ArrayList<>();
    }
}

