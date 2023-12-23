package com.unicauca.gestion.Domain.UseCases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.core.io.Resource;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import com.unicauca.gestion.Apliccation.Input.ManageCourseCUIntPort;
import com.unicauca.gestion.Apliccation.Output.ExceptionFormatterIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageCourseGatewayIntPort;
import com.unicauca.gestion.Domain.Models.AcademicSemester;
import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.Student;

public class ManageCourseCUImplAdapter implements ManageCourseCUIntPort{
    private final ManageCourseGatewayIntPort gatewayCourse;
    private final ExceptionFormatterIntPort formatterCourse;
    private final String uploadDir;

    public ManageCourseCUImplAdapter(ManageCourseGatewayIntPort gatewayCourse,
                                        ExceptionFormatterIntPort formatterCourse){
        this.gatewayCourse = gatewayCourse;
        this.formatterCourse = formatterCourse;
        this.uploadDir = "C:\\Estudio\\AplicacionGestionUsuarios\\BackEnd-GestionUsuarios\\GestorArchivos";
    }

    @Override
    public Course saveCourse(Course course) {
        Course objCourse = null;
        if(this.gatewayCourse.existsById(course.getCodeCourse())){
            this.formatterCourse.returnResponseErrorEntityExists("Error, course already exists in the system");
        }else{
            if(!course.isValidAcademicSemester(this.gatewayCourse.findAllAcademicSemester())){
                this.formatterCourse.returnResponseBusinessRuleViolated("Error, Academic semester is not valid");
            }else{
                objCourse = this.gatewayCourse.save(course);
            }
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
            if(!course.isValidAcademicSemester(this.gatewayCourse.findAllAcademicSemester())){
                this.formatterCourse.returnResponseBusinessRuleViolated("Error, Academic semester is not valid");
            }else{
                objCourse.setName(course.getName());
                objCourse.setObjAcademicSemester(course.getObjAcademicSemester());
                this.gatewayCourse.save(objCourse);
            }
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
                if(course.hasProfessor()){
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
                if(!course.hasProfessor()){
                    this.formatterCourse.returnResponseBusinessRuleViolated("Error, course doesn't have a professor");
                }else{
                    if(!course.isTheProfessor(idProfessor)){
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
    public Course addStudentToCourse(long idStudent, long idCourse) {
        Course objCourse = null;
        if(!this.gatewayCourse.existsById(idCourse)){
            this.formatterCourse.returnResponseErrorEntityNotFound("Error, course was not found");
        }else{
            if(this.gatewayCourse.existsUser(idStudent) == 0){
                this.formatterCourse.returnResponseErrorEntityNotFound("Error, student was not found");
            }else{
                Course course = this.gatewayCourse.findById(idCourse);
                Student student = this.gatewayCourse.findStudentById(idStudent);
                if(course.studentsIsAlreadyMatriculated(idStudent)){
                    this.formatterCourse.returnResponseBusinessRuleViolated("Error, Student already has been matriculated in this course");
                }else{
                    course.addStudent(student);
                    objCourse = this.gatewayCourse.save(course);
                } 
            }
        }
        return objCourse;
    }

    @Override
    public Course deleteStudentFromCourse(long idStudent, long idCourse) {
        Course objCourse = null;
        if(!this.gatewayCourse.existsById(idCourse)){
            this.formatterCourse.returnResponseErrorEntityNotFound("Error, course was not found");
        }else{
            if(this.gatewayCourse.existsUser(idStudent) == 0){
                this.formatterCourse.returnResponseErrorEntityNotFound("Error, student was not found");
            }else{
                Course course = this.gatewayCourse.findById(idCourse);
                Student student = this.gatewayCourse.findStudentById(idStudent);
                if(!course.studentsIsAlreadyMatriculated(idStudent)){
                    this.formatterCourse.returnResponseBusinessRuleViolated("Error, Student has not been matriculated in this course");
                }else{
                    course.deleteStudent(student);
                    objCourse = this.gatewayCourse.save(course);
                } 
            }
        }
        return objCourse;
    }

    @Override
    public List<AcademicSemester> getAcademicSemester() {
        return this.gatewayCourse.findAllAcademicSemester();
    }

    @Override
    public Course uploadFile(long idCourse, MultipartFile file) throws FileUploadException {
        Course course = null;
        if(!this.gatewayCourse.existsById(idCourse)){
            this.formatterCourse.returnResponseErrorEntityNotFound("Error, entity not found");
        }else{
            course = this.gatewayCourse.findById(idCourse);
            if(course.getObjProfessor() == null){
                this.formatterCourse.returnResponseErrorEntityNotFound("Error, course doesn't have a professor");
            }else{
                try {
                    Path uploadPath = Path.of(uploadDir);
                    if (Files.notExists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }
                    String originalFileName = file.getOriginalFilename();
                    String extensionFile = getExtensionFile(originalFileName);
                    if(extensionFile.equals("docx") == false){
                        this.formatterCourse.retunrResponseBadFormat("Error, format file don't support");
                    }else{
                        String fileName = "Contenido_" + course.getName() + "." + extensionFile;
                        Path filePath = uploadPath.resolve(fileName);
                        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                        course.setRouteFileDrive(filePath.toString());
                        course = this.gatewayCourse.save(course);
                    } 
                } catch (IOException e) {
                    throw new FileUploadException("Error al cargar el archivo", e);
                }
            }
        }
        return course;
    }

    private String getExtensionFile(String originalFileName){
        int lastDotIndex = originalFileName.lastIndexOf('.');
        if (lastDotIndex != -1) {
            String extension = originalFileName.substring(lastDotIndex + 1);
            return extension.toLowerCase();
        } else {
            return null;
        }
    }

    @Override
    public Resource downloadFile(long idCourse) {
        Resource objResource = null;
        if(!this.gatewayCourse.existsById(idCourse)){
            this.formatterCourse.returnResponseErrorEntityNotFound("Error, course was not found");
        }else{
            Course course = this.gatewayCourse.findById(idCourse);
            if(course.getRouteFileDrive() == null){
                this.formatterCourse.returnResponseErrorEntityNotFound("Error, resource not found");
            }else{
                try {
                    String route = course.getRouteFileDrive();
                    objResource = new UrlResource(route);
                }catch (Exception e) {
                    System.out.println("Error");
                }
            }
        }
        return objResource;
    }
}
