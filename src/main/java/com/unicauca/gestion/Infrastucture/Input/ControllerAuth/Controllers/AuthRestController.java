package com.unicauca.gestion.Infrastucture.Input.ControllerAuth.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.gestion.Apliccation.Input.ManageAuthCUIntPort;
import com.unicauca.gestion.Domain.Models.Credentionals;
import com.unicauca.gestion.Domain.Models.Login;
import com.unicauca.gestion.Infrastucture.Input.ControllerAuth.DTORequest.LoginDTORequest;
import com.unicauca.gestion.Infrastucture.Input.ControllerAuth.DTOResponse.CredentionalsDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerAuth.mappers.AuthMapperInfrastuctureDomain;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/apiAuth")
@RequiredArgsConstructor
@Validated
public class AuthRestController {
    private final ManageAuthCUIntPort authCU;
    private final AuthMapperInfrastuctureDomain mapper;

    @PostMapping("/auth")
    @Transactional(readOnly = true)
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTORequest request , BindingResult result){
        Map<String,Object>  response = new HashMap<>();
        CredentionalsDTOResponse objCredentionals;

        if(result.hasErrors()){
			List<String> listaErrores= new ArrayList<>();

			for (FieldError error : result.getFieldErrors()) {
				listaErrores.add("El campo '" + error.getField() +"‘ "+ error.getDefaultMessage());
			}

			response.put("errors", listaErrores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

        try {
            Login login = this.mapper.mapRequestToLogin(request);
            Credentionals credentionals = this.authCU.login(login);
            objCredentionals = this.mapper.mapCredentionalsToResponse(credentionals);
        }catch (DataAccessException e) {
            response.put("mensaje", "Error al obtener las credenciales");
			response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CredentionalsDTOResponse>(objCredentionals,HttpStatus.OK);
        
    }
}
