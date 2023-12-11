package com.unicauca.gestion.Apliccation.Input;

import com.unicauca.gestion.Domain.Models.Student;
import java.util.List;

public interface ManageStudentCUIntPort {
    public Student saveStudent(Student student);
    public List<Student> listStudents(); 
    public Student updateStudent(long idStudent , Student student);
}
