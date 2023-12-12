package com.unicauca.gestion.Infrastucture.Output.Persistence.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "professorTypes")
@Data
@AllArgsConstructor
public class ProfessorTypeEntity {

    @Id
    private long idProfessorType;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objProfessorType")
    private List<ProfessorEntity> proffesors;

    public ProfessorTypeEntity(){
        this.proffesors = new ArrayList<>();
    }
}
