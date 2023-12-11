package com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTORequest;

import java.util.List;

import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTORequest.RoleDTORequest;
import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTORequest.UserDTORequest;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTORequest extends UserDTORequest {

    @NotNull(message = "codeStudent can't be null")
    @Digits(integer = 12, fraction = 0, message = "codeStudent must have 12 digits")
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
