package com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTORequest;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfessorTypeDTORequest {
    @Positive(message = "Id professor type can't be null")
    private long idProfessorType;

    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be empty")
    private String name;
    private List<ProfessorDTORequest> proffesors;

    public ProfessorTypeDTORequest(){
        this.proffesors = new ArrayList<>();
    }
}
