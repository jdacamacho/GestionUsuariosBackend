package com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTORequest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfessorTypeDTORequest {
    private long idProfessorType;
    private String name;
    //private List<ProfessorDTORequest> proffesors;

    public ProfessorTypeDTORequest(){
        //this.proffesors = new ArrayList<>();
    }
}
