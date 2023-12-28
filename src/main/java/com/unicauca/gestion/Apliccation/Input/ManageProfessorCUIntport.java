package com.unicauca.gestion.Apliccation.Input;

import java.util.List;

import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.ProfessorType;
import com.unicauca.gestion.Domain.Models.Role;
import com.unicauca.gestion.Domain.Models.Student;

public interface ManageProfessorCUIntport {
    public Professor saveProfessor(Professor professor);
    public List<Professor> listProfessors();
    public List<Professor> listProfessors(long idProfessor);
    public Professor updateProfessor(long idProfessor , Professor professor);
    public Professor getProfessor(long idProfessor);
    public List<Role> getRoles();
    public List<ProfessorType> getProfessorTypes();
    public List<Student> getStudentsFromCourse(long idProfessor,long idCourse);
    public List<Course> getCourseFromProfessor(long idProfessor);
}