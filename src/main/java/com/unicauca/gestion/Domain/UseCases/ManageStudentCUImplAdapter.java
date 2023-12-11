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
        if(gatewayStudent.existsById(student.getIdUser())){
            return this.gatewayStudent.save(student);
        }else{
            return null;
        }
    }
    
}
