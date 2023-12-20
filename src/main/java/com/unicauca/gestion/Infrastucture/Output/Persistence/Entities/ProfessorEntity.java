package com.unicauca.gestion.Infrastucture.Output.Persistence.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "professors")
@Getter
@Setter
public class ProfessorEntity extends UserEntity{

    @Column(unique = true)
    private long codeProfessor;

    @ManyToOne
    @JoinColumn(name = "idProfessorType")
    private ProfessorTypeEntity objProfessorType;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objProfessor")
    private List<CourseEntity> courses;

    public ProfessorEntity(){
        this.courses = new ArrayList<>();
    }

    public ProfessorEntity(long idUser,String names,String lastNames,String email,
                   String username,String password,long numberPhone, List<RoleEntity> roles,
                   long codeProfessor,ProfessorTypeEntity objProfessorType){
        super(idUser, names, lastNames, email, username, password,numberPhone,"Habilitado",roles);
        this.codeProfessor = codeProfessor;
        this.objProfessorType = objProfessorType;
        this.courses = new ArrayList<>();
    }

    public ProfessorEntity(long idUser,String names,String lastNames,String email,Long codeProfessor){
        super(idUser,names,lastNames,email);
        this.codeProfessor = codeProfessor;
    }

}
