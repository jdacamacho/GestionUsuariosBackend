package com.unicauca.gestion.Apliccation.Output;

import java.util.List;

import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.ProfessorType;
import com.unicauca.gestion.Domain.Models.Role;

public interface ManageProfesorGatewayIntPort {
    public List<Professor> findAll();
    public List<Professor> findAll(long idProfessor);
    public boolean existsById(long idProfessor);
    public Professor save(Professor professor);
    public Professor findById(long idProfessor);
    public long existsByIdUserEmailOrUsername(long idUser,String email,String uernam);
    public List<Role> findAllRoles();
    public List<ProfessorType> findAllProfessorTypes();
    public boolean existByCodeProfessor(long codeProfessor);
}