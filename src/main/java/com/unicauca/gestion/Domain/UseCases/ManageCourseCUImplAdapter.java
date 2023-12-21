package com.unicauca.gestion.Domain.UseCases;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.unicauca.gestion.Apliccation.Input.ManageCourseCUIntPort;
import com.unicauca.gestion.Apliccation.Output.ExceptionFormatterIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageCourseGatewayIntPort;
import com.unicauca.gestion.Domain.Models.AcademicSemester;
import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Domain.Models.Professor;

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
    public Course updateCourse(long idCourse, Course course) {
        Course objCourse = null;
        if(!this.gatewayCourse.existsById(idCourse)){
            this.formatterCourse.returnResponseErrorEntityNotFound("Error, course was not found");
        }else{
            objCourse = this.gatewayCourse.findById(idCourse);
            objCourse.setName(course.getName());
            objCourse.setObjAcademicSemester(course.getObjAcademicSemester());
            this.gatewayCourse.save(objCourse);
        }  
        return objCourse;
    }

    @Override
    public Course setProfessorToCourse(long idProfessor, long idCourse) {
        Course objCourse = null;
        if(!this.gatewayCourse.existsById(idCourse)){
            this.formatterCourse.returnResponseErrorEntityNotFound("Error, course not fount");
        }else{
            if(this.gatewayCourse.existsUser(idProfessor) == 0){
                this.formatterCourse.returnResponseErrorEntityNotFound("Error, user not found");
            }else{
                Professor professor = this.gatewayCourse.findProfessorById(idProfessor);
                Course course = this.gatewayCourse.findById(idCourse);
                System.out.println("Aqui" + professor.getNames());
                if(course.getObjProfessor() != null){
                    this.formatterCourse.returnResponseBusinessRuleViolated("Error, course only can have one professor");
                }else{
                    course.setObjProfessor(professor);
                    objCourse = this.gatewayCourse.save(course);
                }
            }
        }
        return objCourse;
    }

    @Override
    public Course unsetProfessorFromCourse(long idProfessor, long idCourse) {
        Course objCourse = null;
        if(!this.gatewayCourse.existsById(idCourse)){
            this.formatterCourse.returnResponseErrorEntityNotFound("Error, course not fount");
        }else{
            if(this.gatewayCourse.existsUser(idProfessor) == 0){
                this.formatterCourse.returnResponseErrorEntityNotFound("Error, user not found");
            }else{
                Course course = this.gatewayCourse.findById(idCourse);
                if(course.getObjProfessor() == null){
                    this.formatterCourse.returnResponseBusinessRuleViolated("Error, course doesn't have a professor");
                }else{
                    if(course.getObjProfessor().getIdUser() != idProfessor){
                        this.formatterCourse.returnResponseBusinessRuleViolated("Error, idprofessor doesn't match with profesor's course");
                    }else{
                        course.setObjProfessor(null);
                        objCourse = this.gatewayCourse.save(course);
                    }
                }
            }
        }
        return objCourse;
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
    public List<AcademicSemester> getAcademicSemester() {
        return this.gatewayCourse.findAllAcademicSemester();
    }

    @Override
    public Course uploadFile(long idCourse, MultipartFile file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'uploadFile'");
    }

    
}
