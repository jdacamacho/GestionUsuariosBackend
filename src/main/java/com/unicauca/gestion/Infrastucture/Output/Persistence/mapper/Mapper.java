package com.unicauca.gestion.Infrastucture.Output.Persistence.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.unicauca.gestion.Domain.Models.AcademicSemester;
import com.unicauca.gestion.Domain.Models.Address;
import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.ProfessorType;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.AcademicSemesterEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.AddressEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.CourseEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorEntity;
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
        
        TypeMap<ProfessorEntity,Professor> mapProfessor = mapper.emptyTypeMap(ProfessorEntity.class, Professor.class);
        mapProfessor.addMappings(m -> m.skip(Professor::setCourses)).implicitMappings();

        TypeMap<AcademicSemesterEntity,AcademicSemester> mapAcademicSemester = mapper.emptyTypeMap(AcademicSemesterEntity.class,AcademicSemester.class);
        mapAcademicSemester.addMappings(m -> m.skip(AcademicSemester::setCourses)).implicitMappings();

        TypeMap<CourseEntity,Course> mapCourse = mapper.emptyTypeMap(CourseEntity.class, Course.class);
        mapCourse.addMappings(m -> m.skip(Course::setStudents)).implicitMappings();

        return mapper;
    }
}