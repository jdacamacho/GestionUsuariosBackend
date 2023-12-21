package com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTORequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AcademicSemesterDTORequest {
    @Positive(message = "id must be positive and not null")
    private long idAcademicSemester;

    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be empty")
    private String name;

    public AcademicSemesterDTORequest(){

    }
}
