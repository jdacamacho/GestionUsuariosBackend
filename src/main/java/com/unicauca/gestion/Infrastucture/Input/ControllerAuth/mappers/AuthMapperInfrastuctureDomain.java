package com.unicauca.gestion.Infrastucture.Input.ControllerAuth.mappers;

import org.mapstruct.Mapper;

import com.unicauca.gestion.Domain.Models.Credentionals;
import com.unicauca.gestion.Domain.Models.Login;
import com.unicauca.gestion.Infrastucture.Input.ControllerAuth.DTORequest.LoginDTORequest;
import com.unicauca.gestion.Infrastucture.Input.ControllerAuth.DTOResponse.CredentionalsDTOResponse;

@Mapper(componentModel = "spring")
public interface AuthMapperInfrastuctureDomain {
    Login mapRequestToLogin(LoginDTORequest loginRequest);
    CredentionalsDTOResponse mapCredentionalsToResponse(Credentionals credentionals);
}
