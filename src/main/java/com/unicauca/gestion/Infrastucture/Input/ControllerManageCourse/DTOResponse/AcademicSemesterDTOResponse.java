package com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class AcademicSemesterDTOResponse {
    private long idAcademicSemester;
    private String name;
    private List<CourseDTOResponse> courses;

    public AcademicSemesterDTOResponse(){
        this.courses = new ArrayList<>();
    }
}
