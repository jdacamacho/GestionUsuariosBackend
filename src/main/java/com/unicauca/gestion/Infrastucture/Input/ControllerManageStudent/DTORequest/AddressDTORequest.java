package com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTORequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTORequest {
    private long idUser;
    
    private StudentDTORequest objStudent;

    @NotNull(message = "residental address can't be null")
    @NotBlank(message = "residental address can't empty")
    private String residentalAddress;

    @NotNull(message = "city address can't be null")
    @NotBlank(message = "city can't empty")
    private String city;

    @NotNull(message = "country address can't be null")
    @NotBlank(message = "country can't empty")
    private String country;

    public AddressDTORequest(){

    }
}
