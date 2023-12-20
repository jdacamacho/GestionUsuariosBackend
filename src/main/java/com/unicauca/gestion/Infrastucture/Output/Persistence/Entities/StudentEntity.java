package com.unicauca.gestion.Infrastucture.Output.Persistence.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
public class StudentEntity extends UserEntity {
    
    @Column(unique = true)
    private long codeStudent;

    @OneToOne(mappedBy = "objStudent",fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    private AddressEntity address;

    public StudentEntity(){

    }

    public StudentEntity(long idUser,String names,String lastNames,String email,
                   String username,String password,long numberPhone, List<RoleEntity> roles,
                   long codeStudent,AddressEntity address){
        super(idUser, names, lastNames, email, username, password,numberPhone,"Habilitado",roles);
        this.codeStudent = codeStudent;
        this.address = address;
    }

    public StudentEntity(Long idUser,String names,String lastNames,String email,long codeStudent){
        super(idUser,names,lastNames,email);
        this.codeStudent = codeStudent;
    }

}
