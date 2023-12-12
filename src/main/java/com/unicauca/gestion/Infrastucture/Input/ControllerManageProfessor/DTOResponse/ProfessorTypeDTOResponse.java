package com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTOResponse;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfessorTypeDTOResponse {
    private long idProfessorType;
    private String name;
    private List<ProfessorDTOResponse> proffesors;

    public ProfessorTypeDTOResponse(){
        this.proffesors = new ArrayList<>();
    }
}
