package com.unicauca.gestion.Infrastucture.Output.Persistence.Gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.unicauca.gestion.Apliccation.Output.ManageProfesorGatewayIntPort;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories.ProfessorRepository;

@Service
public class ManageProfessorGatewayImplAdapter implements ManageProfesorGatewayIntPort{

    private final ProfessorRepository serviceAccessBD;
    private final ModelMapper mapperProfessor;

    public ManageProfessorGatewayImplAdapter(ProfessorRepository serviceAccessBD,
                                            ModelMapper mapperProfessor){
        this.serviceAccessBD = serviceAccessBD;
        this.mapperProfessor = mapperProfessor;
    }

    @Override
    public List<Professor> findAll() {
        Iterable<ProfessorEntity> itarableProfessor = this.serviceAccessBD.findAll();
        List<Professor> obtainedList = this.mapperProfessor.map(itarableProfessor,new TypeToken<List<Professor>>(){
        }.getType());
        return obtainedList;
    }

    @Override
    public boolean existsById(long idProfessor) {
        return this.serviceAccessBD.findById(idProfessor).isPresent();
    }

    @Override
    public Professor save(Professor professor) {
        ProfessorEntity professorToSave = this.mapperProfessor.map(professor, ProfessorEntity.class);
        ProfessorEntity professorSaved = this.serviceAccessBD.save(professorToSave);
        Professor professorResponse = this.mapperProfessor.map(professorSaved, Professor.class);
        return professorResponse;
    }

    @Override
    public Professor findById(long idProfessor) {
        ProfessorEntity obtainedProfessor = this.serviceAccessBD.findById(idProfessor).get();
        Professor professorResponse = this.mapperProfessor.map(obtainedProfessor, Professor.class);
        return professorResponse;
    }

    @Override
    public boolean existsByCodeProfessorEmailOrUsernam(long codeProfessor, String email, String username) {
        Boolean flagResponse = false;
        ProfessorEntity obtainedProfessor = this.serviceAccessBD.findByCodeProfessorOrEmailOrUsername(codeProfessor, email, username);
        if(obtainedProfessor != null) flagResponse = true;
        return flagResponse;
    }
    
}
