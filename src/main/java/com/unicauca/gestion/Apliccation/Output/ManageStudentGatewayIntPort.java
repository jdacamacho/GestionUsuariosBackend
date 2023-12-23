package com.unicauca.gestion.Apliccation.Output;

import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Domain.Models.Role;
import com.unicauca.gestion.Domain.Models.Student;

import java.util.List;


public interface ManageStudentGatewayIntPort {
    public List<Student> findAll();
    public boolean existsById(long idStudent);
    public Student save(Student student);
    public Student findById(long idStudent);
    public long existsByIdUserEmailOrUsername(long idUser,String email,String username);
    public List<Role> findAllRoles();
    public boolean existByCodeStudent(long codeStudent);
    public List<Course> getCoursesStudent(long idStudent);
}
