package com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.unicauca.gestion.Apliccation.Input.ManageCourseCUIntPort;
import com.unicauca.gestion.Domain.Models.AcademicSemester;
import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTORequest.CourseDTORequest;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTOResponse.AcademicSemesterDTOResponse;
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
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<CourseDTOResponse>> list(){
        List<Course> courses = this.courseCU.listCourses();
        ResponseEntity<List<CourseDTOResponse>> objResponse = new ResponseEntity<List<CourseDTOResponse>>(
            this.mapper.mapCoursesToResponse(courses),HttpStatus.OK);
        return objResponse;
    }

    @GetMapping("/courses/academicSemesters")
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<AcademicSemesterDTOResponse>> getAcademicSemesters(){
        List<AcademicSemester> academicSemesters = this.courseCU.getAcademicSemester();
        ResponseEntity<List<AcademicSemesterDTOResponse>> objResponse = new ResponseEntity<List<AcademicSemesterDTOResponse>>(
            this.mapper.mapAcademicSemestersToResponse(academicSemesters),HttpStatus.OK);
        return objResponse;
    }

    @PostMapping("/courses")
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('Administrador')")
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

    @PutMapping("/courses/{codeCourse}")
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> update(@PathVariable long codeCourse,@Valid @RequestBody CourseDTORequest courseRequest, BindingResult result){
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
            objCourse = this.mapper.mapCourseToResponse(this.courseCU.updateCourse(codeCourse,course));
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la actualziacion en la base de datos");
			response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CourseDTOResponse>(objCourse,HttpStatus.OK);
    }

    @PatchMapping("/courses/professors")
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> setProfessorToCourse(@RequestParam("idProfessor") long idProfessor, @RequestParam("codeCourse") long codeCourse){
        CourseDTOResponse objCourse;
        Map<String,Object> response = new HashMap<>();
        
        try {
            objCourse = this.mapper.mapCourseToResponse(this.courseCU.setProfessorToCourse(idProfessor, codeCourse));
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la actualziacion en la base de datos");
			response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CourseDTOResponse>(objCourse,HttpStatus.OK);
    }

    @DeleteMapping("/courses/professors")
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> unsetProfessorToCourse(@RequestParam("idProfessor") long idProfessor, @RequestParam("codeCourse") long codeCourse){
        CourseDTOResponse objCourse;
        Map<String,Object> response = new HashMap<>();
        
        try {
            objCourse = this.mapper.mapCourseToResponse(this.courseCU.unsetProfessorFromCourse(idProfessor, codeCourse));
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la actualziacion en la base de datos");
			response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CourseDTOResponse>(objCourse,HttpStatus.OK);
    }

    @PatchMapping("/courses/students")
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> matriculateStundent(@RequestParam("idStudent") long idStudent,@RequestParam("idCourse") long idCourse){
        CourseDTOResponse objCourse;
        Map<String,Object> response = new HashMap<>();
        
        try {
            objCourse = this.mapper.mapCourseToResponse(this.courseCU.addStudentToCourse(idStudent, idCourse));
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la actualziacion en la base de datos");
			response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CourseDTOResponse>(objCourse,HttpStatus.OK);
    }

    @DeleteMapping("/courses/students")
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> cancelMatriculateStundent(@RequestParam("idStudent") long idStudent,@RequestParam("idCourse") long idCourse){
        CourseDTOResponse objCourse;
        Map<String,Object> response = new HashMap<>();
        
        try {
            objCourse = this.mapper.mapCourseToResponse(this.courseCU.deleteStudentFromCourse(idStudent, idCourse));
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la actualziacion en la base de datos");
			response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CourseDTOResponse>(objCourse,HttpStatus.OK);
    }

    @PatchMapping("/file/upload")
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('Docente')")
    public ResponseEntity<?> uploadFile(@RequestParam("idCourse") long idCourse,@RequestParam("file") MultipartFile file) throws FileUploadException{
        Course course = this.courseCU.uploadFile(idCourse, file);
        ResponseEntity<CourseDTOResponse> objResponse = new ResponseEntity<CourseDTOResponse>(
            this.mapper.mapCourseToResponse(course),HttpStatus.OK);
        return objResponse;
    }

}
