package com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.Mapper;

import org.mapstruct.Mapper;
import java.util.List;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTORequest.ProfessorDTORequest;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTOResponse.ProfessorDTOResponse;

@Mapper(componentModel = "spring")
public interface ProfessorMapperInfrastuctureDomain {
    Professor mapRequestToProfessor(ProfessorDTORequest professorRequest);
    ProfessorDTOResponse mapProfessorToResponse(Professor professor);
    List<ProfessorDTOResponse> mapProfessorsToResponse(List<Professor> professors);
}
