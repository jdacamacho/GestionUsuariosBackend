package com.unicauca.gestion.Infrastucture.Input.UserDTO.DTORequest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UserDTORequest {

    @Positive(message = "Identification must be positive and major than zero")
    @Min(value = 1000000000L, message = "Identification must have at least ten digits" )
    @Max(value = 9999999999L, message = "Identification can't have more than ten digits" )
    private long idUser;

    @NotNull(message = "Names can't be null" )
    @NotBlank(message = "Names can't be empty")
    @Size(min = 4, max = 20, message = "Names can't be shorter than 4 characters and longer than 20 characters")
    private String names;

    @NotNull(message = "LastNames can't be null" )
    @NotBlank(message = "LastNames can't be empty")
    @Size(min = 2, max = 20, message = "LastNames can't be shorter than 2 characters and longer than 20 characters")
    private String lastNames;

    @NotNull(message = "Email can't be null" )
    @NotBlank(message = "Email can't be empty")
    @Email(message = "email must be valid")
    private String email;

    @NotNull(message = "Username can't be null" )
    @NotBlank(message = "Username can't be empty")
    @Size(min = 4 , max = 10 , message = "Username can't be shorter than 4 characters and longer than 10 characters")
    private String username;

    @Size(min = 4 , max = 20 , message = "Username can't be shorter than 4 characters and longer than 20 characters")
    @NotNull(message = "Password can't be null" )
    @NotBlank(message = "Password can't be empty")
    private String password;

    @Min(value = 3000000000L, message = "Number phone must have a '3' at the beginning and a total of 10 digits")
    @Max(value = 3999999999L, message = "Number phone must have a '3' at the beginning and a total of 10 digits")
    private long numberPhone;
    
    @NotNull(message = "State can't be null" )
    @NotBlank(message = "State can't be empty")
    private String state;

    @Size(min = 1, message = "User must have at least one role")
    @Valid
    private List<RoleDTORequest> roles;

    public UserDTORequest(){
        this.roles = new ArrayList<>();
    }
}

