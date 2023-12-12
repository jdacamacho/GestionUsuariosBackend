package com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorEntity;

public interface ProfessorRepository extends CrudRepository<ProfessorEntity,Long>{
    ProfessorEntity findByCodeProfessorOrEmailOrUsername(long code,String email,String username);
}
