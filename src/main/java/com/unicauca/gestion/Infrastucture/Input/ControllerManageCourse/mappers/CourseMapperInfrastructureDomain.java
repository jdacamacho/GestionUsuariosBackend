package com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.mappers;

import org.mapstruct.Mapper;
import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTORequest.CourseDTORequest;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTOResponse.CourseDTOResponse;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapperInfrastructureDomain {
    Course mapRequestToCourse(CourseDTORequest courseRequest);
    CourseDTOResponse mapCourseToResponse(Course course);
    List<CourseDTOResponse> mapCoursesToResponse(List<Course> courses);
}
