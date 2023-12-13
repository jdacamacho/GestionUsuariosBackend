package com.unicauca.gestion.Apliccation.Output;

import com.unicauca.gestion.Domain.Models.Role;
import com.unicauca.gestion.Domain.Models.Student;
import java.util.List;

public interface ManageStudentGatewayIntPort {
    public List<Student> findAll();
    public boolean existsById(long idStudent);
    public Student save(Student student);
    public Student findById(long idStudent);
    public boolean existsByCodeStudentEmailOrUsername(long codeStudent,String email,String username);
    public List<Role> findAllRoles();
}
