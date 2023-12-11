package com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity,Long>{
    StudentEntity findByCodeStudentOrEmailOrUsername(long idStudent,String email, String username);
}
