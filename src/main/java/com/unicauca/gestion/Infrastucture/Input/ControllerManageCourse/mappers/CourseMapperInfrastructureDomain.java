package com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.mappers;

import org.mapstruct.Mapper;

import com.unicauca.gestion.Domain.Models.AcademicSemester;
import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.Student;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTORequest.CourseDTORequest;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTOResponse.AcademicSemesterDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTOResponse.CourseDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTOResponse.ProfessorDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTOResponse.StudentDTOResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapperInfrastructureDomain {
    Course mapRequestToCourse(CourseDTORequest courseRequest);
    CourseDTOResponse mapCourseToResponse(Course course);
    List<CourseDTOResponse> mapCoursesToResponse(List<Course> courses);
    List<AcademicSemesterDTOResponse> mapAcademicSemestersToResponse(List<AcademicSemester> academicSemesters);
    List<StudentDTOResponse> mapStudenstToResponse(List<Student> students);
    List<ProfessorDTOResponse> mapProfessorsToResponse(List<Professor> professors);
}
