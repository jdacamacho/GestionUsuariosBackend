package com.unicauca.gestion.Domain.UseCases;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.unicauca.gestion.Apliccation.Input.ManageStudentCUIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageStudentGatewayIntPort;
import com.unicauca.gestion.Apliccation.Output.ExceptionFormatterIntPort;
import com.unicauca.gestion.Domain.Models.Role;
import com.unicauca.gestion.Domain.Models.Student;
import com.unicauca.gestion.Infrastucture.JWT.JwtService;

public class ManageStudentCUImplAdapter implements ManageStudentCUIntPort {

    private final ManageStudentGatewayIntPort gatewayStudent;
    private final ExceptionFormatterIntPort formatterStudent;
    private final JwtService jwtService;
    private final PasswordEncoder passworEncoder;
    private final AuthenticationManager authenticationManager;

    public ManageStudentCUImplAdapter(ManageStudentGatewayIntPort gatewayStudent,
                                        ExceptionFormatterIntPort formatterStudent,
                                        JwtService jwtService,PasswordEncoder passwordEncoder,
                                        AuthenticationManager authenticationManager){
        this.gatewayStudent = gatewayStudent;
        this.formatterStudent = formatterStudent;
        this.jwtService = jwtService;
        this.passworEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Student saveStudent(Student student) {
        Student objStudent = null;
        if(gatewayStudent.existsByIdUserEmailOrUsername(student.getIdUser(), student.getEmail(), student.getUsername()) > 0){
            this.formatterStudent.returnResponseErrorEntityExists("Error, There is a user with that idUser,email or username in the System");
        }else{
            if(this.gatewayStudent.existByCodeStudent(student.getCodeStudent())){
                this.formatterStudent.returnResponseErrorEntityExists("Error, entity exists with tha student code");
            }else{
                if (student.stateIsValid() == false) {
                    this.formatterStudent.returnResponseBusinessRuleViolated("Error, State is not valid");
                } else if (!student.isValidRole(this.gatewayStudent.findAllRoles())) {
                    this.formatterStudent.returnResponseBusinessRuleViolated("Error, role no valid to a Student");
                } else {
                    String passwordEncode = this.passworEncoder.encode(student.getPassword());
                    student.setPassword(passwordEncode);
                    objStudent = this.gatewayStudent.save(student);
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

    @Override
    public String login(String username, String password) {
        String token = "";
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        UserDetails user = this.gatewayStudent.userToToken(username).orElseThrow() ;
        token = this.jwtService.getToken(user);
        return token;
    }
    
}
