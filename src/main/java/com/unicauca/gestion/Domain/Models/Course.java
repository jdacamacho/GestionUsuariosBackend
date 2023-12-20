package com.unicauca.gestion.Domain.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    private long codeCourse;
    private String name;
    private String routeFileDrive;
    private Professor objProfessor;
    private AcademicSemester objAcademicSemester;

    public Course(){
        
    }


}
