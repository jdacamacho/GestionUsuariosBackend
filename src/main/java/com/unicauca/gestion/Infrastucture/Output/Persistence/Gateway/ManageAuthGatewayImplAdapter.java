package com.unicauca.gestion.Infrastucture.Output.Persistence.Gateway;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.unicauca.gestion.Apliccation.Output.ManageAuthGatewayIntPort;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.UserEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories.UserRepository;

@Service
public class ManageAuthGatewayImplAdapter implements ManageAuthGatewayIntPort{

    private final UserRepository serviceAccessBD;

    public ManageAuthGatewayImplAdapter(UserRepository serviceAccessBD){
        this.serviceAccessBD = serviceAccessBD;
    }

    @Override
    public Optional<UserEntity> findByUserEmail(String username) {
        return this.serviceAccessBD.findByUsername(username);
    }
 
}
