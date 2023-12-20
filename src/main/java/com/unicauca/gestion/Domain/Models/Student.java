package com.unicauca.gestion.Domain.Models;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends User {
    private long codeStudent;
    private Address address;
    private List<Course> courses;

    public Student(){
        this.courses = new ArrayList<>();
    }

    public Student(long idUser,String names,String lastNames,String email,
                   String username,String password,long numberPhone ,List<Role> roles,
                   long codeStudent,Address address){
        super(idUser, names, lastNames, email, username, password,numberPhone,"Habilitado",roles);
        this.codeStudent = codeStudent;
        this.address = address;
        this.courses = new ArrayList<>();
    }
}
