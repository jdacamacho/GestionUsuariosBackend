package com.unicauca.gestion.Infrastucture.Output.Persistence.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.unicauca.gestion.Domain.Models.Address;
import com.unicauca.gestion.Domain.Models.Student;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.AddressEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.StudentEntity;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

@Configuration
public class Mapper {
    @Bean
    public ModelMapper createMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<AddressEntity,Address> mapAddress = mapper.emptyTypeMap(AddressEntity.class, Address.class);
        mapAddress.addMappings(m -> m.skip(Address::setObjStudent)).implicitMappings();
        //TypeMap<StudentEntity,Student> mapStudent = mapper.emptyTypeMap(StudentEntity.class, Student.class);
        //mapStudent.addMappings(m -> m.skip(Student::setAddress)).implicitMappings();
        return mapper;
    }
}