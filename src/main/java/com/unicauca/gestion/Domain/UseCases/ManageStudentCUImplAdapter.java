package com.unicauca.gestion.Domain.UseCases;

import java.util.List;

import com.unicauca.gestion.Apliccation.Input.ManageStudentCUIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageStudentGatewayIntPort;
import com.unicauca.gestion.Domain.Models.Student;

public class ManageStudentCUImplAdapter implements ManageStudentCUIntPort {

    private final ManageStudentGatewayIntPort gatewayStudent;

    public ManageStudentCUImplAdapter(ManageStudentGatewayIntPort gatewayStudent){
        this.gatewayStudent = gatewayStudent;
    }

    @Override
    public Student saveStudent(Student student) {
        if(gatewayStudent.existsById(student.getIdUser())){
            return null;
        }else{
            return this.gatewayStudent.save(student);
        }
    }

    @Override
    public List<Student> listStudents() {
        return this.gatewayStudent.findAll();
    }

    @Override
    public Student updateStudent(long idStudent, Student student) {
        if(this.gatewayStudent.existsById(idStudent)){
            Student obtainedStudent = this.gatewayStudent.findById(idStudent);
            obtainedStudent.setIdUser(student.getIdUser());
            obtainedStudent.setNames(student.getNames());
            obtainedStudent.setLastNames(student.getLastNames());
            obtainedStudent.setEmail(student.getEmail());
            obtainedStudent.setUsername(student.getUsername());
            obtainedStudent.setPassword(obtainedStudent.getPassword());
            obtainedStudent.setNumberPhone(student.getNumberPhone());
            obtainedStudent.setCodeStudent(student.getCodeStudent());
            obtainedStudent.setState(student.getState());
            obtainedStudent.setRoles(student.getRoles());
            obtainedStudent.setAddress(student.getAddress());
            return this.gatewayStudent.save(obtainedStudent);
        }
        return null;
    }
    
}
