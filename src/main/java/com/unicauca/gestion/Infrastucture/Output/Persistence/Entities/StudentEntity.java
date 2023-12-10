package com.unicauca.gestion.Infrastucture.Output.Persistence.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "students")
@Getter
@Setter
public class StudentEntity extends UserEntity {
    
    @Column(unique = true)
    @NotNull(message = "codeStudent can't be null")
    @Size(min = 10,max = 10, message = "codeStudent must have a size of 10")
    private long codeStudent;

    @OneToOne(mappedBy = "objStudent",fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    private AddressEntity address;

    public StudentEntity(){

    }

    public StudentEntity(long idUser,String names,String lastNames,String email,
                   String username,String password,long numberPhone, List<RoleEntity> roles,
                   long codeStudent,AddressEntity address){
        super(idUser, names, lastNames, email, username, password,numberPhone,roles);
        this.codeStudent = codeStudent;
        this.address = address;
    }

}
