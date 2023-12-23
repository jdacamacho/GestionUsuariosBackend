package com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.CourseEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorTypeEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.RoleEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.StudentEntity;

public interface ProfessorRepository extends CrudRepository<ProfessorEntity,Long>{

    @Query("from RoleEntity r WHERE r.name <> 'ROLE_Estudiante'")
    List<RoleEntity> findAllRoles();

    @Query("from ProfessorTypeEntity")
    List<ProfessorTypeEntity> findAllProfessorType();

    @Query("SELECT COUNT(u) FROM UserEntity u WHERE u.idUser = :idUser OR u.email = :email OR u.username = :username")
    long countByIdUserOrEmailOrUsername(@Param("idUser") long idUser, @Param("email") String email, @Param("username") String username);

    @Query("from CourseEntity")
    List<CourseEntity> findAllCourses();

    @Query("from StudentEntity")
    List<StudentEntity> findAllStundents();

    ProfessorEntity findByCodeProfessor(long codeProfessor);
}
