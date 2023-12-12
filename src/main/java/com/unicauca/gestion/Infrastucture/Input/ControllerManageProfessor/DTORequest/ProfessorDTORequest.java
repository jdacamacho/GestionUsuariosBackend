package com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTORequest;

import java.util.List;

import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTORequest.RoleDTORequest;
import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTORequest.UserDTORequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorDTORequest extends UserDTORequest {
    private long codeProfessor;
    private ProfessorTypeDTORequest objProfessorType;

    public ProfessorDTORequest(){

    }

    public ProfessorDTORequest(long idUser,String names,String lastNames,String email,
                   String username,String password,long numberPhone, List<RoleDTORequest> roles,
                   long codeProfessor,ProfessorTypeDTORequest objProfessorType){
        super(idUser, names, lastNames, email, username,password,numberPhone,"Habilitado",roles);
        this.codeProfessor = codeProfessor;
        this.objProfessorType = objProfessorType;
    } 
}
