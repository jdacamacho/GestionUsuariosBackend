package com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTOResponse;

import java.util.List;

import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTOResponse.RoleDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTOResponse.UserDTOResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTOResponse extends UserDTOResponse {
    private long codeStudent;
    private AddressDTOResponse address;

    public StudentDTOResponse(){

    }

    public StudentDTOResponse(long idUser,String names,String lastNames,String email,
                   String username,long numberPhone ,List<RoleDTOResponse> roles,
                   long codeStudent,AddressDTOResponse address){
        super(idUser, names, lastNames, email, username,numberPhone,"Habilitado",roles);
        this.codeStudent = codeStudent;
        this.address = address;
    }

}
