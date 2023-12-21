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

    public boolean isValidAcademicSemester(List<AcademicSemester> academicSemesters){
        for (AcademicSemester academicSemester : academicSemesters) {
            if(academicSemester.equals(this.objAcademicSemester)){
                return true;
            }
        }
        return false;
    }

    public boolean hasProfessor(){
        if(this.objProfessor != null){
            return true;
        }
        return false;
    }

    public boolean isTheProfessor(long idProfessor){
        if(this.objProfessor.getIdUser() != idProfessor){
            return false;
        }
        return true;
    }

}
