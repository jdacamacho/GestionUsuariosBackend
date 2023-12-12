package com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTOResponse {
    private long idUser;
    private StudentDTOResponse objStudent;
    private String residentalAddress;
    private String city;
    private String country;

    public AddressDTOResponse(){

    }
}
