package com.unicauca.gestion.Infrastucture.Output.Persistence.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ProfessorEntity extends UserEntity{

    @Column(unique = true)
    private long codeProfessor;

    @ManyToOne
    @JoinColumn(name = "idProfessorType")
    private ProfessorTypeEntity objProfessorType;

    public ProfessorEntity(long idUser,String names,String lastNames,String email,
                   String username,String password,long numberPhone, List<RoleEntity> roles,
                   long codeProfessor,ProfessorTypeEntity objProfessorType){
        super(idUser, names, lastNames, email, username, password,numberPhone,"Habilitado",roles);
        this.codeProfessor = codeProfessor;
        this.objProfessorType = objProfessorType;
    }

}
