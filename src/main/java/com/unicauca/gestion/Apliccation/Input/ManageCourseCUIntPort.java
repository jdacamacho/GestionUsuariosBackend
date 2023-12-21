package com.unicauca.gestion.Apliccation.Input;

import com.unicauca.gestion.Domain.Models.Course;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ManageCourseCUIntPort {
    public Course saveCourse(Course course);
    public List<Course> listCourses();
    public Course updateCourse(long idCourse, Course Course);
    public Course setProfessorToCourse(long idProfessor,long idCourse);
    public Course unsetProfessorFromCourse(long idProfessor,long idCourse);
    public Course addStudentToCouser(long idStudent,long idCourse);
    public Course deleteStudentFromCourse(long idStudent,long idCourse);
    public Course uploadFile(long idCourse, MultipartFile file);
}
