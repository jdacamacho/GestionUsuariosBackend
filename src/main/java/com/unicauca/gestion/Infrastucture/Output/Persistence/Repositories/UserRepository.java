package com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Long>{
    Optional<UserEntity> findByUsername(String username);
}
