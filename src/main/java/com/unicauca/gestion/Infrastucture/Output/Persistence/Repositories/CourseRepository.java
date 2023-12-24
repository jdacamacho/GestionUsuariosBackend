package com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.AcademicSemesterEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.CourseEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.StudentEntity;

public interface CourseRepository extends CrudRepository<CourseEntity,Long>{
    @Query("from AcademicSemesterEntity")
    List<AcademicSemesterEntity> findAllAcademicSemesters();

    @Query("SELECT COUNT(u) FROM UserEntity u WHERE u.idUser =:idUser ")
    long CountByIdUser(@Param("idUser") long idUser);

    @Query("SELECT new StudentEntity(s.idUser, s.names, s.lastNames, s.email, s.codeStudent) FROM StudentEntity s WHERE s.idUser =:idUser")
    StudentEntity findStudentById(@Param("idUser") long idUser);

    @Query("SELECT new ProfessorEntity(p.idUser, p.names, p.lastNames, p.email, p.codeProfessor) FROM ProfessorEntity p WHERE p.idUser =:idUser")
    ProfessorEntity findProfessorById(@Param("idUser") long idUser);

    @Query("from ProfessorEntity")
    List<ProfessorEntity> findAllProfessors();

    @Query("from StudentEntity")
    List<StudentEntity> findAllStundents();

}
