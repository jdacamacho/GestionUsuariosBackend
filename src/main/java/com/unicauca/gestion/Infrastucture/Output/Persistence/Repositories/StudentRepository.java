package com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.RoleEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.StudentEntity;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity,Long>{

    @Query("from RoleEntity r WHERE r.name <> 'Administrador' AND r.name <> 'Docente'")
    List<RoleEntity> findAllRoles();

    StudentEntity findByCodeStudentOrEmailOrUsername(long idStudent,String email, String username);
}
