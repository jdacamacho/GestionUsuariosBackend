package com.unicauca.gestion.Domain.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Professor extends User{

    private long codeProfessor;
    private ProfessorType objProfessorType;
    private List<Course> courses;

    public Professor(){
        this.courses = new ArrayList<>();
    }

    public Professor(long idUser,String names,String lastNames,String email,
                   String username,String password,long numberPhone, List<Role> roles,
                   long codeProfessor,ProfessorType objProfessorType){
        super(idUser, names, lastNames, email, username, password,numberPhone,"Habilitado",roles);
        this.codeProfessor = codeProfessor;
        this.objProfessorType = objProfessorType;
        this.courses = new ArrayList<>();
    } 

    public boolean isValidProfessorType(List<ProfessorType> professorTypes){
        for (ProfessorType professorType : professorTypes) {
            if(this.objProfessorType.equals(professorType)) return true;
        }
        return false;
    }


}
