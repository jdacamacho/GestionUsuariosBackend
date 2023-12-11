package com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.mappers;

import org.mapstruct.Mapper;
import java.util.List;
import com.unicauca.gestion.Domain.Models.Student;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTORequest.StudentDTORequest;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTOResponse.StudentDTOResponse;

@Mapper(componentModel = "spring")
public interface StudentMapperInfrastuctureDomain {
    Student mapRequestToStudent(StudentDTORequest student);
    StudentDTOResponse mapStudentToResponse(Student student);
    List<StudentDTOResponse> mapStudentsToResponse(List<Student> students);
}
