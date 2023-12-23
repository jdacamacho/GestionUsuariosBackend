package com.unicauca.gestion.Domain.UseCases;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.unicauca.gestion.Apliccation.Input.ManageAuthCUIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageAuthGatewayIntPort;
import com.unicauca.gestion.Domain.Models.Credentionals;
import com.unicauca.gestion.Domain.Models.Login;
import com.unicauca.gestion.Infrastucture.JWT.JwtService;
import com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.OwnException.BadCredentionalsException;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.RoleEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.UserEntity;

public class ManageAuthCUImplAdapter implements ManageAuthCUIntPort{

    private final ManageAuthGatewayIntPort gatewayAuth;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ManageAuthCUImplAdapter(ManageAuthGatewayIntPort gatewayAuth,
                                    JwtService jwtService,AuthenticationManager authenticationManager){
        this.gatewayAuth = gatewayAuth;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Credentionals login(Login login) {
        Credentionals credentionals = new Credentionals();
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
            if (authentication.isAuthenticated()) {
                Optional<UserEntity> userBD = this.gatewayAuth.findByUserEmail(login.getUsername());
                UserDetails user = userBD.orElseThrow();
                String token = this.jwtService.getToken(user);
                List<String> roleR = roleCredentionals(userBD.get().getRoles());
                credentionals.setIdUser(userBD.get().getIdUser());
                credentionals.setUsername(userBD.get().getUsername());
                credentionals.setEmail(userBD.get().getEmail());
                credentionals.setAccess(roleR);
                credentionals.setToken(token);
            }
        } catch (BadCredentialsException ex) {
            throw new BadCredentionalsException("Error, checkout credentionals");
        }
        return credentionals;
    }

    private List<String> roleCredentionals(List<RoleEntity> roles){
        List<String> rolesResponse = new ArrayList<>();
        for (RoleEntity role : roles) {
            rolesResponse.add(role.getName());
        }
        return rolesResponse;
    }
    
}
