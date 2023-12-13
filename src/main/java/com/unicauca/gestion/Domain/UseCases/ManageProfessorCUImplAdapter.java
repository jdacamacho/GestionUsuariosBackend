package com.unicauca.gestion.Domain.UseCases;

import java.util.List;

import com.unicauca.gestion.Apliccation.Input.ManageProfessorCUIntport;
import com.unicauca.gestion.Apliccation.Output.ExceptionFormatterIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageProfesorGatewayIntPort;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.ProfessorType;
import com.unicauca.gestion.Domain.Models.Role;

public class ManageProfessorCUImplAdapter implements ManageProfessorCUIntport {

    private final ManageProfesorGatewayIntPort gatewayProfessor;
    private final ExceptionFormatterIntPort formatterProfessor;

    public ManageProfessorCUImplAdapter(ManageProfesorGatewayIntPort gatewayProfessor,
                                        ExceptionFormatterIntPort formatterProfessor){
        this.gatewayProfessor = gatewayProfessor;
        this.formatterProfessor = formatterProfessor;
    }

    @Override
    public Professor saveProfessor(Professor professor) {
        Professor objProfessor = null;
        if(this.gatewayProfessor.existsById(professor.getIdUser())){
            this.formatterProfessor.returnResponseErrorEntityExists("Entity already exists in the System");
        }else{
            if(this.gatewayProfessor.existsByCodeProfessorEmailOrUsernam(professor.getCodeProfessor(), professor.getEmail(), professor.getUsername())){
                this.formatterProfessor.returnResponseErrorEntityExists("There is an entity with that codeProfessor,email or username");
            }else{
                objProfessor = this.gatewayProfessor.save(professor);
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
        Professor objStudent = null;
        if(!this.gatewayProfessor.existsById(idProfessor)){
            this.formatterProfessor.returnResponseErrorEntityNotFound("Entity not found");
        }else{
            Professor obtainedProfessor = this.gatewayProfessor.findById(idProfessor);
            if(existsCodeStudetEmailUsernameValid(obtainedProfessor,professor)){
                this.formatterProfessor.returnResponseErrorEntityExists("Entity with that code professor,email or usernam already exists");
            }else{
                obtainedProfessor.setNames(professor.getNames());
                obtainedProfessor.setLastNames(professor.getLastNames());
                obtainedProfessor.setEmail(professor.getEmail());
                obtainedProfessor.setUsername(professor.getUsername());
                obtainedProfessor.setNumberPhone(professor.getNumberPhone());
                obtainedProfessor.setCodeProfessor(professor.getCodeProfessor());
                obtainedProfessor.setState(professor.getState());
                obtainedProfessor.setRoles(professor.getRoles());
                obtainedProfessor.setCodeProfessor(professor.getCodeProfessor());
                obtainedProfessor.setObjProfessorType(professor.getObjProfessorType());
                objStudent = this.gatewayProfessor.save(obtainedProfessor);
            }
        }
        return objStudent;
    }

    private boolean existsCodeStudetEmailUsernameValid(Professor obtainedProfessor,
                                                       Professor newProfessor){

        long codeStudent = 0;
        String email = "youWon'tFindThisEmail";
        String username = "youWon'tFindThisUserName";

        if(obtainedProfessor.getCodeProfessor() != newProfessor.getCodeProfessor()) codeStudent = newProfessor.getCodeProfessor();
        if(obtainedProfessor.getEmail().equals(newProfessor.getEmail()) == false) email = newProfessor.getEmail();
        if(obtainedProfessor.getUsername().equals(newProfessor.getUsername()) == false) username = newProfessor.getUsername();

        return this.gatewayProfessor.existsByCodeProfessorEmailOrUsernam(codeStudent, email, username) ;
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
        return this.gatewayProfessor.findAllProfessorType();
    }
}
