package com.unicauca.gestion.Infrastucture.Output.Persistence.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.unicauca.gestion.Domain.Models.AcademicSemester;
import com.unicauca.gestion.Domain.Models.Address;
import com.unicauca.gestion.Domain.Models.ProfessorType;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.AcademicSemesterEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.AddressEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorTypeEntity;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

@Configuration
public class Mapper {
    @Bean
    public ModelMapper createMapper(){

        ModelMapper mapper = new ModelMapper();
        TypeMap<AddressEntity,Address> mapAddress = mapper.emptyTypeMap(AddressEntity.class, Address.class);
        mapAddress.addMappings(m -> m.skip(Address::setObjStudent)).implicitMappings();
        
        TypeMap<ProfessorTypeEntity,ProfessorType> mapProfessorType = mapper.emptyTypeMap(ProfessorTypeEntity.class, ProfessorType.class);
        mapProfessorType.addMappings(m -> m.skip(ProfessorType::setProffesors)).implicitMappings();

        TypeMap<AcademicSemesterEntity,AcademicSemester> mapAcademicSemester = mapper.emptyTypeMap(AcademicSemesterEntity.class,AcademicSemester.class);
        mapAcademicSemester.addMappings(m -> m.skip(AcademicSemester::setCourses)).implicitMappings();

        return mapper;
    }
}