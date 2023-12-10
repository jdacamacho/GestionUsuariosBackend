package com.unicauca.gestion.Domain.Models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends User {
    private long codeStudent;
    private Address address;

    public Student(){

    }

    public Student(long idUser,String names,String lastNames,String email,
                   String username,String password, List<Role> roles,
                   long codeStudent,Address address){
        super(idUser, names, lastNames, email, username, password,roles);
        this.codeStudent = codeStudent;
        this.address = address;
    }

}
