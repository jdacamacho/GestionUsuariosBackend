package com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.Controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.gestion.Apliccation.Input.ManageStudentCUIntPort;
import com.unicauca.gestion.Domain.Models.Student;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTORequest.StudentDTORequest;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTOResponse.StudentDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.mappers.StudentMapperInfrastuctureDomain;
import com.unicauca.gestion.Infrastucture.Input.UserDTO.DTOResponse.RoleDTOResponse;

import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import com.unicauca.gestion.Domain.Models.Role;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/apiStudent")
@Validated
@RequiredArgsConstructor
public class StudentRestController {
    
    private final ManageStudentCUIntPort studentCU;
    private final StudentMapperInfrastuctureDomain mapper;

    @PostMapping("/students/auth")
    @Transactional(readOnly = true)
    public ResponseEntity<?> login(@RequestParam("username") String username,@RequestParam("password") String password){
        ResponseEntity<String> objResponse = new ResponseEntity<String>(this.studentCU.login(username, password),HttpStatus.OK);
        return objResponse;
    }

    @GetMapping("/students")
    @PreAuthorize("hasRole('Administrador')")
    @Transactional(readOnly = true)
    public ResponseEntity<List<StudentDTOResponse>> list(){
        List<Student> students = this.studentCU.listStudents();
        ResponseEntity<List<StudentDTOResponse>> objResponse = new ResponseEntity<List<StudentDTOResponse>>(
            mapper.mapStudentsToResponse(students),HttpStatus.OK);
        return objResponse;
    }

    @GetMapping("/students/roles")
    @PreAuthorize("hasRole('Administrador')")
    @Transactional(readOnly = true)
    public ResponseEntity<List<RoleDTOResponse>> getRoles(){
        List<Role> roles = this.studentCU.getRoles();
        ResponseEntity<List<RoleDTOResponse>> objResponse = new ResponseEntity<List<RoleDTOResponse>>(
            mapper.mapRoleToResponse(roles),HttpStatus.OK);
        return objResponse;
    }

    @GetMapping("/students/{idStudent}")
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> getStudent(@PathVariable long idStudent){
        Student student = this.studentCU.getStudent(idStudent);
        ResponseEntity<StudentDTOResponse> objResponse = new ResponseEntity<StudentDTOResponse>(
            mapper.mapStudentToResponse(student),HttpStatus.OK);
        return objResponse;
    }

    @PostMapping("/students")
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> create(@Valid @RequestBody StudentDTORequest studentRequest, BindingResult result){
        Student student = this.mapper.mapRequestToStudent(studentRequest);
        student.getAddress().setObjStudent(student);
        Map<String,Object> response = new HashMap<>();
        StudentDTOResponse objStudent;

        if(result.hasErrors()){
			List<String> listaErrores= new ArrayList<>();

			for (FieldError error : result.getFieldErrors()) {
				listaErrores.add("El campo '" + error.getField() +"‘ "+ error.getDefaultMessage());
			}

			response.put("errors", listaErrores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

        try {
            objStudent = this.mapper.mapStudentToResponse(this.studentCU.saveStudent(student));
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la inserción en la base de datos");
			response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<StudentDTOResponse>(objStudent, HttpStatus.OK);
    }

    @PutMapping("/students/{idStudent}")
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> update (@PathVariable long idStudent,@Valid @RequestBody StudentDTORequest studentRequest,BindingResult result){
        Student student = this.mapper.mapRequestToStudent(studentRequest);
        student.getAddress().setObjStudent(student);
        Map<String,Object> response = new HashMap<>();
        StudentDTOResponse objStudent;

        if(result.hasErrors()){
			List<String> listaErrores= new ArrayList<>();

			for (FieldError error : result.getFieldErrors()) {
				listaErrores.add("El campo '" + error.getField() +"‘ "+ error.getDefaultMessage());
			}

			response.put("errors", listaErrores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

        try {
            objStudent = this.mapper.mapStudentToResponse(this.studentCU.updateStudent(idStudent, student) );
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la actualizacion en la base de datos");
			response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<StudentDTOResponse>(objStudent, HttpStatus.OK);
    }
}
