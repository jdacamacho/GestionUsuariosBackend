package com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTOResponse;

import java.util.List;

import com.unicauca.gestion.Infrastucture.Input.UserDTO.RoleDTO;
import com.unicauca.gestion.Infrastucture.Input.UserDTO.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTOResponse extends UserDTO {
    private long codeStudent;
    private AddressDTOResponse address;

    public StudentDTOResponse(){

    }

    public StudentDTOResponse(long idUser,String names,String lastNames,String email,
                   String username,String password,long numberPhone ,List<RoleDTO> roles,
                   long codeStudent,AddressDTOResponse address){
        super(idUser, names, lastNames, email, username, password,numberPhone,"Habilitado",roles);
        this.codeStudent = codeStudent;
        this.address = address;
    }

}
