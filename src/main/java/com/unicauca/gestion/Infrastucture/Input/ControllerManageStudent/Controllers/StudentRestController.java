package com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.Controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.gestion.Apliccation.Input.ManageStudentCUIntPort;
import com.unicauca.gestion.Domain.Models.Student;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTORequest.StudentDTORequest;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTOResponse.StudentDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.mappers.StudentMapperInfrastuctureDomain;

import jakarta.validation.Valid;

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

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTOResponse>> list(){
        List<Student> students = this.studentCU.listStudents();
        ResponseEntity<List<StudentDTOResponse>> objResponse = new ResponseEntity<List<StudentDTOResponse>>(
            mapper.mapStudentsToResponse(students),HttpStatus.OK);
        return objResponse;
    }

    @PostMapping("/students")
    public ResponseEntity<?> create(@Valid @RequestBody StudentDTORequest studentRequest){
        Student student = this.mapper.mapRequestToStudent(studentRequest);
        student.getAddress().setObjStudent(student);
        Map<String,Object> response = new HashMap<>();
        StudentDTOResponse objStudent;

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
    public ResponseEntity<?> update (@PathVariable long idStudent,@Valid @RequestBody StudentDTORequest studentRequest){
        Student student = this.mapper.mapRequestToStudent(studentRequest);
        student.getAddress().setObjStudent(student);
        Map<String,Object> response = new HashMap<>();
        StudentDTOResponse objStudent;

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
