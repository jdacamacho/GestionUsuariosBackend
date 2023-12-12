package com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTOResponse;

import java.util.List;

import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTOResponse.RoleDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTOResponse.UserDTOResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorDTOResponse extends UserDTOResponse {
    private long codeProfessor;
    private ProfessorTypeDTOResponse objProfessorType;

    public ProfessorDTOResponse(){

    }

    public ProfessorDTOResponse(long idUser,String names,String lastNames,String email,
                   String username,long numberPhone, List<RoleDTOResponse> roles,
                   long codeProfessor,ProfessorTypeDTOResponse objProfessorType){
        super(idUser, names, lastNames, email, username,numberPhone,"Habilitado",roles);
        this.codeProfessor = codeProfessor;
        this.objProfessorType = objProfessorType;
    } 
}
