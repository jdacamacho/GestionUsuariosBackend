package com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.mappers;

import org.mapstruct.Mapper;
import java.util.List;

import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Domain.Models.Role;
import com.unicauca.gestion.Domain.Models.Student;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTOResponse.CourseDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTORequest.StudentDTORequest;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTOResponse.StudentDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTOResponse.RoleDTOResponse;

@Mapper(componentModel = "spring")
public interface StudentMapperInfrastuctureDomain {
    Student mapRequestToStudent(StudentDTORequest studentRequest);
    StudentDTOResponse mapStudentToResponse(Student student);
    List<StudentDTOResponse> mapStudentsToResponse(List<Student> students);
    List<RoleDTOResponse> mapRoleToResponse(List<Role> roles);
    List<CourseDTOResponse> mapCourseToResponse(List<Course> courses);

}
