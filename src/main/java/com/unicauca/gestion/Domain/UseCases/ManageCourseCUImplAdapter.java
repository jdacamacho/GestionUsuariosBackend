package com.unicauca.gestion.Domain.UseCases;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.unicauca.gestion.Apliccation.Input.ManageCourseCUIntPort;
import com.unicauca.gestion.Apliccation.Output.ExceptionFormatterIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageCourseGatewayIntPort;
import com.unicauca.gestion.Domain.Models.Course;

public class ManageCourseCUImplAdapter implements ManageCourseCUIntPort{
    private final ManageCourseGatewayIntPort gatewayCourse;
    private final ExceptionFormatterIntPort formatterCourse;

    public ManageCourseCUImplAdapter(ManageCourseGatewayIntPort gatewayCourse,
                                        ExceptionFormatterIntPort formatterCourse){
        this.gatewayCourse = gatewayCourse;
        this.formatterCourse = formatterCourse;
    }

    @Override
    public Course saveCourse(Course course) {
        Course objCourse = null;
        if(this.gatewayCourse.existsById(course.getCodeCourse())){
            this.formatterCourse.returnResponseErrorEntityExists("Error, course already exists in the system");
        }else{
            objCourse = this.gatewayCourse.save(course);
        }   
        return objCourse;
    }

    @Override
    public List<Course> listCourses() {
        return this.gatewayCourse.findAll();
    }

    @Override
    public Course updateCourse(long idCourse, Course Course) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCourse'");
    }

    @Override
    public Course setProfessorToCourse(long idProfessor, long idCourse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setProfessorToCourse'");
    }

    @Override
    public Course unsetProfessorFromCourse(long idProfessor, long idCourse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unsetProfessorFromCourse'");
    }

    @Override
    public Course addStudentToCouser(long idStudent, long idCourse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addStudentToCouser'");
    }

    @Override
    public Course deleteStudentFromCourse(long idStudent, long idCourse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteStudentFromCourse'");
    }

    @Override
    public Course uploadFile(long idCourse, MultipartFile file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'uploadFile'");
    }

    
}
