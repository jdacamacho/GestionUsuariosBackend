package com.unicauca.gestion.Domain.Models;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AcademicSemester {
    private long idAcademicSemester;
    private String name;
    private List<Course> courses;

    public AcademicSemester(){
        this.courses = new ArrayList<>();
    }
}
