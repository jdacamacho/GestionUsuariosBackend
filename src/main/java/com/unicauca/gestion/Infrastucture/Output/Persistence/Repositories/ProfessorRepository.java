package com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorTypeEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.RoleEntity;


public interface ProfessorRepository extends CrudRepository<ProfessorEntity,Long>{

    @Query("from RoleEntity r WHERE r.name <> 'Estudiante'")
    List<RoleEntity> findAllRoles();

    @Query("from ProfessorTypeEntity")
    List<ProfessorTypeEntity> findAllProfessorType();

    ProfessorEntity findByCodeProfessorOrEmailOrUsername(long code,String email,String username);
}
