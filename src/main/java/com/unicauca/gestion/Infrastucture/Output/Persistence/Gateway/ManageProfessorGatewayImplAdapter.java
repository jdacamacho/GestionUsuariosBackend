package com.unicauca.gestion.Infrastucture.Output.Persistence.Gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.unicauca.gestion.Apliccation.Output.ManageProfesorGatewayIntPort;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.ProfessorType;
import com.unicauca.gestion.Domain.Models.Role;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorTypeEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.RoleEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories.ProfessorRepository;

@Service
public class ManageProfessorGatewayImplAdapter implements ManageProfesorGatewayIntPort{

    private final ProfessorRepository serviceAccessBD;
    private final ModelMapper mapper;

    public ManageProfessorGatewayImplAdapter(ProfessorRepository serviceAccessBD,
                                            ModelMapper mapper){
        this.serviceAccessBD = serviceAccessBD;
        this.mapper = mapper;
    }

    @Override
    public List<Professor> findAll() {
        Iterable<ProfessorEntity> itarableProfessor = this.serviceAccessBD.findAll();
        List<Professor> obtainedList = this.mapper.map(itarableProfessor,new TypeToken<List<Professor>>(){
        }.getType());
        return obtainedList;
    }

    @Override
    public List<Professor> findAll(long idProfessor) {
        Iterable<ProfessorEntity> itarableProfessor = this.serviceAccessBD.getProfessors(idProfessor);
        List<Professor> obtainedList = this.mapper.map(itarableProfessor,new TypeToken<List<Professor>>(){
        }.getType());
        return obtainedList;
    }

    @Override
    public boolean existsById(long idProfessor) {
        return this.serviceAccessBD.findById(idProfessor).isPresent();
    }

    @Override
    public Professor save(Professor professor) {
        ProfessorEntity professorToSave = this.mapper.map(professor, ProfessorEntity.class);
        ProfessorEntity professorSaved = this.serviceAccessBD.save(professorToSave);
        Professor professorResponse = this.mapper.map(professorSaved, Professor.class);
        return professorResponse;
    }

    @Override
    public Professor findById(long idProfessor) {
        ProfessorEntity obtainedProfessor = this.serviceAccessBD.findById(idProfessor).get();
        Professor professorResponse = this.mapper.map(obtainedProfessor, Professor.class);
        return professorResponse;
    }

    @Override
    public List<Role> findAllRoles() {
        List<RoleEntity> rolesObtained = this.serviceAccessBD.findAllRoles();
        List<Role> roleResponse = this.mapper.map(rolesObtained, new TypeToken<List<Role>>(){
        }.getType());
        return roleResponse;
    }

    @Override
    public List<ProfessorType> findAllProfessorTypes() {
        List<ProfessorTypeEntity> professorTypeObtained = this.serviceAccessBD.findAllProfessorType();
        List<ProfessorType> professorTypeResponse = this.mapper.map(professorTypeObtained, new TypeToken<List<ProfessorType>>(){
        }.getType());
        return professorTypeResponse;
    }

    @Override
    public long existsByIdUserEmailOrUsername(long idUser, String email,
            String username) {
        return this.serviceAccessBD.countByIdUserOrEmailOrUsername(idUser, email, username);
    }

    @Override
    public boolean existByCodeProfessor(long codeProfessor) {
        boolean flagResponse = false;
        ProfessorEntity professor = this.serviceAccessBD.findByCodeProfessor(codeProfessor);
        if(professor !=  null) flagResponse = true;
        return flagResponse;
    }

    
}
