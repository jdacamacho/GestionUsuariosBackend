package com.unicauca.gestion.Domain.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class Course {
    private long codeCourse;
    private String name;
    private String routeFileDrive;
    private Professor objProfessor;
    private AcademicSemester objAcademicSemester;
    private List<Student> students;

    public Course(){
        this.students = new ArrayList<>();
    }


}
