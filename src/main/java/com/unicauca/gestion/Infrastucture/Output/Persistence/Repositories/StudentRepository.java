package com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.CourseEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.RoleEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.StudentEntity;

import java.util.List;
public interface StudentRepository extends CrudRepository<StudentEntity,Long>{

    @Query("from RoleEntity r WHERE r.name <> 'ROLE_Administrador' AND r.name <> 'ROLE_Docente'")
    List<RoleEntity> findAllRoles();

    @Query("SELECT COUNT(u) FROM UserEntity u WHERE u.idUser = :idUser OR u.email = :email OR u.username = :username")
    long countByIdUserOrEmailOrUsername(@Param("idUser") long idUser, @Param("email") String email, @Param("username") String username);

    @Query("SELECT c FROM CourseEntity c JOIN c.students s WHERE s.idUser = :idUser")
    List<CourseEntity> findCoursesByStudentCode(@Param("idUser") long idUser);

    StudentEntity findByCodeStudent(long codeStudent);
}
