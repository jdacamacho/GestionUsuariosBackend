package com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorTypeEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.RoleEntity;

import java.util.List;

public interface ProfessorRepository extends CrudRepository<ProfessorEntity,Long>{

    //@Query("SELECT r FROM RoleEntity r")
    //List<RoleEntity> findRoleEntities();

   // @Query("SELECT p FROM ProfessorTypeEntity p")
    //List<ProfessorTypeEntity> findProfessorTypeEntities();

    ProfessorEntity findByCodeProfessorOrEmailOrUsername(long code,String email,String username);
}
