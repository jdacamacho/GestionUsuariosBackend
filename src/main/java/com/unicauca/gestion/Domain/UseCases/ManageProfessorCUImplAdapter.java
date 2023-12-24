package com.unicauca.gestion.Domain.UseCases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.unicauca.gestion.Apliccation.Input.ManageProfessorCUIntport;
import com.unicauca.gestion.Apliccation.Output.ExceptionFormatterIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageProfesorGatewayIntPort;
import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.ProfessorType;
import com.unicauca.gestion.Domain.Models.Role;
import com.unicauca.gestion.Domain.Models.Student;

public class ManageProfessorCUImplAdapter implements ManageProfessorCUIntport {

    private final ManageProfesorGatewayIntPort gatewayProfessor;
    private final ExceptionFormatterIntPort formatterProfessor;
    private final PasswordEncoder passworEncoder;


    public ManageProfessorCUImplAdapter(ManageProfesorGatewayIntPort gatewayProfessor,
                                        ExceptionFormatterIntPort formatterProfessor,
                                        PasswordEncoder passwordEncoder){
        this.gatewayProfessor = gatewayProfessor;
        this.formatterProfessor = formatterProfessor;
        this.passworEncoder = passwordEncoder;
    }

    @Override
    public Professor saveProfessor(Professor professor) {
        Professor objProfessor = null;
        if(this.gatewayProfessor.existsByIdUserEmailOrUsername(professor.getIdUser(), professor.getEmail(), professor.getUsername()) > 0){
            this.formatterProfessor.returnResponseErrorEntityExists("There is an entity with that id User,email or username");
        }else{
            if(this.gatewayProfessor.existByCodeProfessor(professor.getCodeProfessor())){
                this.formatterProfessor.returnResponseErrorEntityExists("Error, there is an entity with that code professor");
            }else{
                if (!professor.stateIsValid()) {
                    this.formatterProfessor.returnResponseBusinessRuleViolated("Error,state is not valid");
                } else if (!professor.isValidRole(this.gatewayProfessor.findAllRoles())) {
                    this.formatterProfessor.returnResponseBusinessRuleViolated("Error, role is not valid");
                } else if (!professor.isValidProfessorType(this.gatewayProfessor.findAllProfessorTypes())) {
                    this.formatterProfessor.returnResponseBusinessRuleViolated("Error, professor type is not valid");
                } else {
                    String passworEncode = this.passworEncoder.encode(professor.getPassword());
                    professor.setPassword(passworEncode);
                    objProfessor = this.gatewayProfessor.save(professor);
                }
            } 
        }
        return objProfessor;
    }

    @Override
    public List<Professor> listProfessors() {
        return this.gatewayProfessor.findAll();
    }

    @Override
    public Professor updateProfessor(long idProfessor, Professor professor) {
        Professor objProfessor = null;
        if(this.gatewayProfessor.existsByIdUserEmailOrUsername(professor.getIdUser(), professor.getEmail(), professor.getUsername()) <= 0){
            this.formatterProfessor.returnResponseErrorEntityExists("Error entity was not found");
        }else{
            Professor obtainedProfessor = this.gatewayProfessor.findById(idProfessor);
            if(existsIdUserEmailUsernameValid(obtainedProfessor,professor) > 0){
                this.formatterProfessor.returnResponseErrorEntityExists("Entity with that code professor,email or usernam already exists");
            }else{
                if(!professor.stateIsValid()){
                    this.formatterProfessor.returnResponseBusinessRuleViolated("Error,state is not valid");
                }else if(!professor.isValidRole(this.gatewayProfessor.findAllRoles())){
                    this.formatterProfessor.returnResponseBusinessRuleViolated("Error, role is not valid");
                }else if(!professor.isValidProfessorType(this.gatewayProfessor.findAllProfessorTypes())){
                    this.formatterProfessor.returnResponseBusinessRuleViolated("Error, professor type is not valid");
                }else{
                    obtainedProfessor.setNames(professor.getNames());
                    obtainedProfessor.setLastNames(professor.getLastNames());
                    obtainedProfessor.setEmail(professor.getEmail());
                    obtainedProfessor.setUsername(professor.getUsername());
                    obtainedProfessor.setNumberPhone(professor.getNumberPhone());
                    obtainedProfessor.setCodeProfessor(professor.getCodeProfessor());
                    obtainedProfessor.setState(professor.getState());
                    obtainedProfessor.setRoles(professor.getRoles());
                    obtainedProfessor.setObjProfessorType(professor.getObjProfessorType());
                    objProfessor = this.gatewayProfessor.save(obtainedProfessor);
                }
            }
        }
        return objProfessor;
    }

    private long existsIdUserEmailUsernameValid(Professor obtainedProfessor,
                                                       Professor newProfessor){

        long idUser = 0;
        String email = "youWon'tFindThisEmail";
        String username = "youWon'tFindThisUserName";

        if(obtainedProfessor.getEmail().equals(newProfessor.getEmail()) == false) email = newProfessor.getEmail();
        if(obtainedProfessor.getUsername().equals(newProfessor.getUsername()) == false) username = newProfessor.getUsername();

        return this.gatewayProfessor.existsByIdUserEmailOrUsername(idUser, email, username) ;
    }

    @Override
    public Professor getProfessor(long idProfessor) {
        Professor professor = null;
        if(!this.gatewayProfessor.existsById(idProfessor)){
            this.formatterProfessor.returnResponseErrorEntityNotFound("ERROR, entity wasn't found");
        }else{
            professor = this.gatewayProfessor.findById(idProfessor);
        }   
        return professor;
    }

    @Override
    public List<Role> getRoles() {
        return this.gatewayProfessor.findAllRoles();
    }

    @Override
    public List<ProfessorType> getProfessorTypes() {
       return this.gatewayProfessor.findAllProfessorTypes();
    }

    @Override
    public List<Course> getCourses() {
        return this.gatewayProfessor.findAllCourses();
    }

    @Override
    public List<Student> getStudents() {
        return this.gatewayProfessor.findAllStudents();
    }

    @Override
    public List<Student> getStudentsFromCourse(long idProfessor,long idCourse) {
        List<Student> students = new ArrayList<>();
        if(!this.gatewayProfessor.existsById(idProfessor)){
            this.formatterProfessor.returnResponseErrorEntityNotFound("Professor was not found");
        }else{
            Professor professor = this.gatewayProfessor.findById(idProfessor);
            List<Course> coursesProfessor = professor.getCourses();
            Course course = findCourse(coursesProfessor, idCourse);
            students = course.getStudents();
        }
        return students;
    }

    @Override
    public List<Course> getCourseFromProfessor(long idProfessor) {
        List<Course> courses = new ArrayList<>();
        if(!this.gatewayProfessor.existsById(idProfessor)){
            this.formatterProfessor.returnResponseErrorEntityNotFound("Error, professor not found");
        }else{
            Professor professor = this.gatewayProfessor.findById(idProfessor);
            courses = professor.getCourses();
        }
        return courses;
    }

    private Course findCourse(List<Course> courses, long idCourse){
        Course objCourse = null;
        for (Course course : courses) {
            if(course.getCodeCourse() == idCourse){
                return course;
            }
        }
        return objCourse;
    }

}
