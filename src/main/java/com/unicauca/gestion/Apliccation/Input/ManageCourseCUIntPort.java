package com.unicauca.gestion.Apliccation.Input;

import com.unicauca.gestion.Domain.Models.AcademicSemester;
import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.Student;

import java.util.List;

import org.springframework.core.io.Resource;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface ManageCourseCUIntPort {
    public Course saveCourse(Course course);
    public List<Course> listCourses();
    public Course updateCourse(long idCourse, Course Course);
    public Course setProfessorToCourse(long idProfessor,long idCourse);
    public Course unsetProfessorFromCourse(long idProfessor,long idCourse);
    public Course addStudentToCourse(long idStudent,long idCourse);
    public Course deleteStudentFromCourse(long idStudent,long idCourse);
    public List<AcademicSemester> getAcademicSemester();
    public Course uploadFile(long idCourse, MultipartFile file) throws FileUploadException;
    public Resource downloadFile(long idCourse);
    public List<Student> getStudents();
    public List<Professor> getProfessors();
}
