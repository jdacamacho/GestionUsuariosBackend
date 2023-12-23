package com.unicauca.gestion.Apliccation.Output;

import java.util.List;
import java.util.Optional;

import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.ProfessorType;
import com.unicauca.gestion.Domain.Models.Role;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.UserEntity;;

public interface ManageProfesorGatewayIntPort {
    public List<Professor> findAll();
    public boolean existsById(long idProfessor);
    public Professor save(Professor professor);
    public Professor findById(long idProfessor);
    public long existsByIdUserEmailOrUsername(long idUser,String email,String uernam);
    public List<Role> findAllRoles();
    public List<ProfessorType> findAllProfessorTypes();
    public boolean existByCodeProfessor(long codeProfessor);
    Professor login(String username,String password);
    boolean existsByLogin(String username,String password);
    public Optional<UserEntity> userToToken(String username);
}