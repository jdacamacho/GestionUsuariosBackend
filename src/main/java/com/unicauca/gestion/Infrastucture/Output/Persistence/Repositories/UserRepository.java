package com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Long>{
    
    @Query("SELECT COUNT(s) FROM StudentEntity s WHERE s.username =:username")
    long credentionalsAreFromStudent(@Param("username") String username);

    @Query("SELECT COUNT(p) FROM ProfessorEntity p WHERE p.username =:username")
    long credentionalsAreFromProfessor(@Param("username") String username);

    Optional<UserEntity> findByUsername(String username);
}
