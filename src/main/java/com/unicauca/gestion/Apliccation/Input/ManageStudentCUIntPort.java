package com.unicauca.gestion.Apliccation.Input;

import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Domain.Models.Role;
import com.unicauca.gestion.Domain.Models.Student;
import java.util.List;

public interface ManageStudentCUIntPort {
    public Student saveStudent(Student student);
    public List<Student> listStudents(); 
    public Student updateStudent(long idStudent , Student student);
    public Student getStudent(long idStudent);
    public List<Role> getRoles();
    public List<Course> getCoursesStudent(long idStudent);
}
