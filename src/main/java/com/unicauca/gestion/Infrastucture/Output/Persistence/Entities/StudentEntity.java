package com.unicauca.gestion.Infrastucture.Output.Persistence.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

}
