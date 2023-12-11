package com.unicauca.gestion.Domain.UseCases;

import java.util.List;

import com.unicauca.gestion.Apliccation.Input.ManageStudentCUIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageStudentGatewayIntPort;
import com.unicauca.gestion.Apliccation.Output.StudentFormatterIntPort;
import com.unicauca.gestion.Domain.Models.Student;

public class ManageStudentCUImplAdapter implements ManageStudentCUIntPort {

    private final ManageStudentGatewayIntPort gatewayStudent;
    private final StudentFormatterIntPort formatterStudent;

    public ManageStudentCUImplAdapter(ManageStudentGatewayIntPort gatewayStudent,
                                        StudentFormatterIntPort formatterStudent){
        this.gatewayStudent = gatewayStudent;
        this.formatterStudent = formatterStudent;
    }

    @Override
    public Student saveStudent(Student student) {
        Student objStudent = null;
        if(gatewayStudent.existsById(student.getIdUser())){
            this.formatterStudent.returnResponseErrorEntityExists("Error, There is a user with that Identificaction in the System");
        }else{
            if(gatewayStudent.existsByCodeStudentEmailOrUsername(student.getCodeStudent(), student.getEmail(), student.getUsername())){
                this.formatterStudent.returnResponseErrorEntityExists("Error, There is a user with that codeStudent,email or username in the System");
            }else{
                if(student.stateIsValid() == false){
                    this.formatterStudent.returnResponseBusinessRuleViolated("Error, State is not valid");
                }else{
                    objStudent =  this.gatewayStudent.save(student);
                }
            }
        }
        return objStudent;
    }

    @Override
    public List<Student> listStudents() {
        return this.gatewayStudent.findAll();
    }

    @Override
    public Student updateStudent(long idStudent, Student student) {
        Student objStudent = null;

        if(this.gatewayStudent.existsById(idStudent) == false ){
            this.formatterStudent.returnResponseErrorEntityNotFound("Error, Entity with that identification doesn't exists");
        }else{
            if(student.stateIsValid() == false){
                this.formatterStudent.returnResponseBusinessRuleViolated("Error, State is not valid");
            }else{
                Student obtainedStudent = this.gatewayStudent.findById(idStudent);
                if(existsCodeStudetEmailUsernameValid(obtainedStudent,student)){
                    this.formatterStudent.returnResponseErrorEntityExists("Error, There is a user with that codeStudent,email or username in the System");
                }else{
                    obtainedStudent.setNames(student.getNames());
                    obtainedStudent.setLastNames(student.getLastNames());
                    obtainedStudent.setEmail(student.getEmail());
                    obtainedStudent.setUsername(student.getUsername());
                    obtainedStudent.setNumberPhone(student.getNumberPhone());
                    obtainedStudent.setCodeStudent(student.getCodeStudent());
                    obtainedStudent.setState(student.getState());
                    obtainedStudent.setRoles(student.getRoles());
                    obtainedStudent.setAddress(student.getAddress());
                    obtainedStudent.getAddress().setIdUser(idStudent);
                    objStudent =  this.gatewayStudent.save(obtainedStudent);
                }
            }
        }
        return objStudent;
    }

    private boolean existsCodeStudetEmailUsernameValid(Student obtainedStudent,
                                                       Student newStudent){
        long codeStudent = 0;
        String email = "youWon'tFindThisEmail";
        String username = "youWon'tFindThisUserName";

        if(obtainedStudent.getCodeStudent() != newStudent.getCodeStudent()) codeStudent = newStudent.getCodeStudent();
        if(obtainedStudent.getEmail().equals(newStudent.getEmail()) == false) email = newStudent.getEmail();
        if(obtainedStudent.getUsername().equals(newStudent.getUsername()) == false) username = newStudent.getUsername();

        return this.gatewayStudent.existsByCodeStudentEmailOrUsername(codeStudent, email, username);
    }

    @Override
    public Student getStudent(long idStudent) {
        Student objStudent = null;
        if(!this.gatewayStudent.existsById(idStudent)){
            this.formatterStudent.returnResponseErrorEntityNotFound("Error, Entity with that identification doesn't exists");
        }else{
            objStudent = this.gatewayStudent.findById(idStudent);
        }
        return objStudent;
    }
    
}
