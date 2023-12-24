package com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.mappers;

import org.mapstruct.Mapper;
import java.util.List;

import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.ProfessorType;
import com.unicauca.gestion.Domain.Models.Role;
import com.unicauca.gestion.Domain.Models.Student;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTOResponse.CourseDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTORequest.ProfessorDTORequest;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTOResponse.ProfessorDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTOResponse.ProfessorTypeDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTOResponse.StudentDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTOResponse.RoleDTOResponse;

@Mapper(componentModel = "spring")
public interface ProfessorMapperInfrastuctureDomain {
    Professor mapRequestToProfessor(ProfessorDTORequest professorRequest);
    ProfessorDTOResponse mapProfessorToResponse(Professor professor);
    List<ProfessorDTOResponse> mapProfessorsToResponse(List<Professor> professors);
    List<RoleDTOResponse> mapRoleToResponse(List<Role> roles);
    List<ProfessorTypeDTOResponse> mapProfessorTypeToResponse(List<ProfessorType> professorTypes); 
}
