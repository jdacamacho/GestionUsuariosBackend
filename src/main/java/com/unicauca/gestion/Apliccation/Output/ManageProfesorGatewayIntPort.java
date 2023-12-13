package com.unicauca.gestion.Apliccation.Output;

import java.util.List;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.ProfessorType;
import com.unicauca.gestion.Domain.Models.Role;

public interface ManageProfesorGatewayIntPort {
    public List<Professor> findAll();
    public boolean existsById(long idProfessor);
    public Professor save(Professor professor);
    public Professor findById(long idProfessor);
    public boolean existsByCodeProfessorEmailOrUsernam(long codeProfessor,String email,String usernam);
    public List<Role> findAllRoles();
    public List<ProfessorType> findAllProfessorType();
}