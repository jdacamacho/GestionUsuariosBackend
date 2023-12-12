package com.unicauca.gestion.Domain.Models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfessorType {
    private long idProfessorType;
    private String name;
    private List<Professor> proffesors;

    public ProfessorType(){
        this.proffesors = new ArrayList<>();
    }
    
}
