package com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.gestion.Apliccation.Input.ManageCourseCUIntPort;
import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTORequest.CourseDTORequest;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTOResponse.CourseDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.mappers.CourseMapperInfrastructureDomain;

import jakarta.validation.Valid;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/apiCourse")
@RequiredArgsConstructor
@Validated
public class CourseRestController {
    private final ManageCourseCUIntPort courseCU;
    private final CourseMapperInfrastructureDomain mapper ;

    @GetMapping("/courses")
    @Transactional(readOnly = true)
    public ResponseEntity<List<CourseDTOResponse>> list(){
        List<Course> courses = this.courseCU.listCourses();
        ResponseEntity<List<CourseDTOResponse>> objResponse = new ResponseEntity<List<CourseDTOResponse>>(
            this.mapper.mapCoursesToResponse(courses),HttpStatus.OK);
        return objResponse;
    }

    @PostMapping("/courses")
    @Transactional(readOnly = false)
    public ResponseEntity<?> save (@Valid @RequestBody CourseDTORequest courseRequest, BindingResult result){
        Course course = this.mapper.mapRequestToCourse(courseRequest);
        Map<String,Object> response = new HashMap<>();
        CourseDTOResponse objCourse;
        
        if(result.hasErrors()){
			List<String> listaErrores= new ArrayList<>();

			for (FieldError error : result.getFieldErrors()) {
				listaErrores.add("El campo '" + error.getField() +"‘ "+ error.getDefaultMessage());
			}

			response.put("errors", listaErrores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

        try {
            objCourse = this.mapper.mapCourseToResponse(this.courseCU.saveCourse(course));
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la inserción en la base de datos");
			response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CourseDTOResponse>(objCourse,HttpStatus.OK);

    }
}
