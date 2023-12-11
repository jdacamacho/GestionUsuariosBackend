package com.unicauca.gestion.Apliccation.Output;

import com.unicauca.gestion.Domain.Models.Student;
import java.util.List;

public interface ManageStudentGatewayIntPort {
    public List<Student> findAll();
    public boolean existsById(long idStudent);
    public Student save(Student student);
    public Student findById(long idStudent);}
