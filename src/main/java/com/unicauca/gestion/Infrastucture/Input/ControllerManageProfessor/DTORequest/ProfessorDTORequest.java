package com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTORequest;

import java.util.List;

import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTORequest.RoleDTORequest;
import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTORequest.UserDTORequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorDTORequest extends UserDTORequest {

    @Positive(message = "codeProfessor must be positive and major than zero")
    @Min(value = 100000000000L, message = "codeProfessor must have at least twelve digits" )
    @Max(value = 999999999999L, message = "codeProfessor can't have more than twelve digits" )
    private long codeProfessor;

    @Valid
    @NotNull(message = "professorType can't be null")
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
