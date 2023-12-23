package com.unicauca.gestion.Apliccation.Output;

import java.util.Optional;

import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.UserEntity;

public interface ManageAuthGatewayIntPort {
    Optional<UserEntity> findByUserEmail(String username);
}
