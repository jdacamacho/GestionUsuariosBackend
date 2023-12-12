package com.unicauca.gestion.Apliccation.Input;

import java.util.List;
import com.unicauca.gestion.Domain.Models.Professor;

public interface ManageProfessorCUIntport {
    public Professor saveProfessor(Professor professor);
    public List<Professor> listProfessors();
    public Professor updateProfessor(long idProfessor , Professor professor);
    public Professor getProfessor(long idProfessor);
}