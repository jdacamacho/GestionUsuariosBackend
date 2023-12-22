package com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.RoleEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.StudentEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.UserEntity;

import java.util.List;
import java.util.Optional;
public interface StudentRepository extends CrudRepository<StudentEntity,Long>{

    @Query("from RoleEntity r WHERE r.name <> 'Administrador' AND r.name <> 'Docente'")
    List<RoleEntity> findAllRoles();

    @Query("SELECT COUNT(u) FROM UserEntity u WHERE u.idUser = :idUser OR u.email = :email OR u.username = :username")
    long countByIdUserOrEmailOrUsername(@Param("idUser") long idUser, @Param("email") String email, @Param("username") String username);

    @Query("from UserEntity u where u.username =:username ")
    Optional<UserEntity> findByUsername(@Param("username") String username);

    StudentEntity findByUsernameAndPassword(String username,String password);

    StudentEntity findByCodeStudent(long codeStudent);
}
