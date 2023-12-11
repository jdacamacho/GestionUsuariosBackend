package com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTORequest;

import java.util.List;


import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTORequest.RoleDTORequest;
import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTORequest.UserDTORequest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTORequest extends UserDTORequest {

    @Positive(message = "codeStudent must be positive and major than zero")
    @Min(value = 100000000000L, message = "codeStudent must have at least twelve digits" )
    @Max(value = 999999999999L, message = "codeStudent can't have more than twelve digits" )
    private long codeStudent;
    
    private AddressDTORequest address;

    public StudentDTORequest(){

    }

    public StudentDTORequest(long idUser,String names,String lastNames,String email,
                   String username,String password,long numberPhone ,List<RoleDTORequest> roles,
                   long codeStudent,AddressDTORequest address){
        super(idUser, names, lastNames, email, username, password,numberPhone,"Habilitado",roles);
        this.codeStudent = codeStudent;
        this.address = address;
    }

}
