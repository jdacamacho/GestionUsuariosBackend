package com.unicauca.gestion.Domain.UseCases;

import java.util.List;

import com.unicauca.gestion.Apliccation.Input.ManageStudentCUIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageStudentGatewayIntPort;
import com.unicauca.gestion.Apliccation.Output.ExceptionFormatterIntPort;
import com.unicauca.gestion.Domain.Models.Role;
import com.unicauca.gestion.Domain.Models.Student;

public class ManageStudentCUImplAdapter implements ManageStudentCUIntPort {

    private final ManageStudentGatewayIntPort gatewayStudent;
    private final ExceptionFormatterIntPort formatterStudent;

    public ManageStudentCUImplAdapter(ManageStudentGatewayIntPort gatewayStudent,
                                        ExceptionFormatterIntPort formatterStudent){
        this.gatewayStudent = gatewayStudent;
        this.formatterStudent = formatterStudent;
    }

    @Override
    public Student saveStudent(Student student) {
        Student objStudent = null;
        if(gatewayStudent.existsByIdUserEmailOrUsername(student.getIdUser(), student.getEmail(), student.getUsername()) > 0){
            this.formatterStudent.returnResponseErrorEntityExists("Error, There is a user with that idUser,email or username in the System");
        }else{
            if (student.stateIsValid() == false) {
                this.formatterStudent.returnResponseBusinessRuleViolated("Error, State is not valid");
            } else if (!student.isValidRole(this.gatewayStudent.findAllRoles())) {
                this.formatterStudent.returnResponseBusinessRuleViolated("Error, role no valid to a Student");
            } else {
                objStudent = this.gatewayStudent.save(student);
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

        if(gatewayStudent.existsByIdUserEmailOrUsername(student.getIdUser(), student.getEmail(), student.getUsername()) <= 0){
            this.formatterStudent.returnResponseErrorEntityNotFound("Error, entity was not found");
        }
        else{
            if(student.stateIsValid() == false){
                this.formatterStudent.returnResponseBusinessRuleViolated("Error, State is not valid");
            }else if(!student.isValidRole(this.gatewayStudent.findAllRoles())){
                this.formatterStudent.returnResponseBusinessRuleViolated("Error, role no valid to a Student");
            }
            else{
                Student obtainedStudent = this.gatewayStudent.findById(idStudent);
                if(existsIdUserEmailUsernameValid(obtainedStudent,student) > 0){
                    this.formatterStudent.returnResponseErrorEntityExists("Error, There is a user with that idUser,email or username in the System");
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

    private long existsIdUserEmailUsernameValid(Student obtainedStudent,
                                                       Student newStudent){

        long idUser = 0;
        String email = "youWon'tFindThisEmail";
        String username = "youWon'tFindThisUserName";

        if(obtainedStudent.getEmail().equals(newStudent.getEmail()) == false) email = newStudent.getEmail();
        if(obtainedStudent.getUsername().equals(newStudent.getUsername()) == false) username = newStudent.getUsername();

        return this.gatewayStudent.existsByIdUserEmailOrUsername(idUser, email, username);
    }

    @Override
    public Student getStudent(long idStudent) {
        Student student = null;
        if(!this.gatewayStudent.existsById(idStudent)){
            this.formatterStudent.returnResponseErrorEntityNotFound("There is no entity with that idStudent");
        }else{
            student = this.gatewayStudent.findById(idStudent);
        }
        return student;
    }

    @Override
    public List<Role> getRoles() {
        return this.gatewayStudent.findAllRoles();
    }
    
}
