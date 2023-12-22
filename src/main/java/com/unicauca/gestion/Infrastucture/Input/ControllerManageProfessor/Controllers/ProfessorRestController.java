package com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.Controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.gestion.Apliccation.Input.ManageProfessorCUIntport;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.ProfessorType;
import com.unicauca.gestion.Domain.Models.Role;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTORequest.ProfessorDTORequest;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTOResponse.ProfessorDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTOResponse.ProfessorTypeDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.mappers.ProfessorMapperInfrastuctureDomain;
import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTOResponse.RoleDTOResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiProfessor")
@Validated
@RequiredArgsConstructor
public class ProfessorRestController {
    private final ManageProfessorCUIntport professorCU;
    private final ProfessorMapperInfrastuctureDomain mapper;

    @PostMapping("/professors/auth")
    @Transactional(readOnly = true)
    public ResponseEntity<?> login(String username,String password){
        Professor professor = this.professorCU.login(username, password);
        ResponseEntity<ProfessorDTOResponse> objResponse = new ResponseEntity<ProfessorDTOResponse>(
            this.mapper.mapProfessorToResponse(professor),HttpStatus.OK);
        return objResponse;
    }

    @GetMapping("/professors")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ProfessorDTOResponse>> list(){
        List<Professor> professors = this.professorCU.listProfessors();
        ResponseEntity<List<ProfessorDTOResponse>> objResponse = new ResponseEntity<List<ProfessorDTOResponse>>(
            this.mapper.mapProfessorsToResponse(professors),HttpStatus.OK);
        return objResponse;
    }

    @GetMapping("/professors/{idProfessor}")
    @Transactional(readOnly = true)
    public ResponseEntity<?> getProfessor(@PathVariable long idProfessor){
        Professor professor = this.professorCU.getProfessor(idProfessor);
        ResponseEntity<ProfessorDTOResponse> objResponse = new ResponseEntity<ProfessorDTOResponse>(
            this.mapper.mapProfessorToResponse(professor),HttpStatus.OK);
        return objResponse;
    }

    @GetMapping("/professors/roles")
    @Transactional(readOnly = true)
    public ResponseEntity<List<RoleDTOResponse>> getRoles(){
        List<Role> roles = this.professorCU.getRoles();
        ResponseEntity<List<RoleDTOResponse>> objResponse = new ResponseEntity<List<RoleDTOResponse>>(
            this.mapper.mapRoleToResponse(roles),HttpStatus.OK);
        return objResponse;
    }

    @GetMapping("/professors/professorsType")
    public ResponseEntity<List<ProfessorTypeDTOResponse>> getProfessorType(){
        List<ProfessorType> professorTypes = this.professorCU.getProfessorTypes();
        ResponseEntity<List<ProfessorTypeDTOResponse>> objResponse = new ResponseEntity<List<ProfessorTypeDTOResponse>>(
            this.mapper.mapProfessorTypeToResponse(professorTypes),HttpStatus.OK);
        return objResponse;
    }

    @PostMapping("/professors")
    @Transactional(readOnly = false)
    public ResponseEntity<?> save (@Valid @RequestBody ProfessorDTORequest professorRequest, BindingResult result){
        Professor professor = this.mapper.mapRequestToProfessor(professorRequest);
        Map<String,Object> response = new HashMap<>();
        ProfessorDTOResponse objProfessor;

        if(result.hasErrors()){
			List<String> listaErrores= new ArrayList<>();

			for (FieldError error : result.getFieldErrors()) {
				listaErrores.add("El campo '" + error.getField() +"‘ "+ error.getDefaultMessage());
			}

			response.put("errors", listaErrores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

        try {
            objProfessor = this.mapper.mapProfessorToResponse(this.professorCU.saveProfessor(professor));
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la inserción en la base de datos");
			response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProfessorDTOResponse>(objProfessor,HttpStatus.OK);
    }

    @PutMapping("/professors/{idProfessor}")
    @Transactional(readOnly = false)
    public ResponseEntity<?> update (@PathVariable long idProfessor,@Valid @RequestBody ProfessorDTORequest professorRequest,BindingResult result){
        Professor professor = this.mapper.mapRequestToProfessor(professorRequest);
        Map<String,Object> response = new HashMap<>();
        ProfessorDTOResponse objProfessor;

        if(result.hasErrors()){
			List<String> listaErrores= new ArrayList<>();

			for (FieldError error : result.getFieldErrors()) {
				listaErrores.add("El campo '" + error.getField() +"‘ "+ error.getDefaultMessage());
			}

			response.put("errors", listaErrores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

        try {
            objProfessor = this.mapper.mapProfessorToResponse(this.professorCU.updateProfessor(idProfessor, professor));
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la actualizacion en la base de datos");
			response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProfessorDTOResponse>(objProfessor, HttpStatus.OK);
    }


}
