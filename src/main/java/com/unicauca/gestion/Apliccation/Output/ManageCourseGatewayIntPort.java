package com.unicauca.gestion.Apliccation.Output;
import java.util.List;

import com.unicauca.gestion.Domain.Models.AcademicSemester;
import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.Student;

public interface ManageCourseGatewayIntPort {
    public List<Course> findAll();
    public Course save(Course course);
    public Course findById(Long idCourse);
    public boolean existsById(long idCourse);
    public List<AcademicSemester> findAllAcademicSemester();
    public long existsUser(long idUser);
    public Professor findProfessorById(long idProfessor);
    public Student findStudentById(long idStudent);
    public List<Student> findAllStudents();
    public List<Professor> findAllProfessors();
}