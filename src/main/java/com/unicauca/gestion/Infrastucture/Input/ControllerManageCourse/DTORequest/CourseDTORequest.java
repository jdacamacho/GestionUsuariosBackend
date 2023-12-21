package com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTORequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDTORequest {
    @Positive(message = "code must be positive")
    private long codeCourse;

    @NotNull(message = "name can't be null")
    @NotBlank(message = "name can't be empty")
    private String name;

    @NotNull(message = "academic semester can't be null")
    @Valid
    private AcademicSemesterDTORequest objAcademicSemester;

    public CourseDTORequest(){
        
    }
}
