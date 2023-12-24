package com.unicauca.gestion.Infrastucture.Output.Persistence.Gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.unicauca.gestion.Apliccation.Output.ManageCourseGatewayIntPort;
import com.unicauca.gestion.Domain.Models.AcademicSemester;
import com.unicauca.gestion.Domain.Models.Course;
import com.unicauca.gestion.Domain.Models.Professor;
import com.unicauca.gestion.Domain.Models.Student;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.AcademicSemesterEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.CourseEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.ProfessorEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.StudentEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories.CourseRepository;

@Service
public class ManageCourseGatewayImplAdapter implements ManageCourseGatewayIntPort{

    private final CourseRepository serviceAccessBD;
    private final ModelMapper mapper;

    public ManageCourseGatewayImplAdapter(CourseRepository serviceAccessBD,
                                            ModelMapper mapper){
        this.serviceAccessBD = serviceAccessBD;
        this.mapper = mapper;
    }

    @Override
    public List<Course> findAll() {
        Iterable<CourseEntity> iterableCourse = this.serviceAccessBD.findAll();
        List<Course> courseResponse = this.mapper.map(iterableCourse, new TypeToken<List<Course>>(){
            }.getType());
        return courseResponse;
    }

    @Override
    public Course save(Course course) {
        CourseEntity courseToSave = this.mapper.map(course,CourseEntity.class);
        CourseEntity courseSave = this.serviceAccessBD.save(courseToSave);
        Course courseResponse = this.mapper.map(courseSave, Course.class);
        return courseResponse;
    }

    @Override
    public Course findById(Long idCourse) {
        CourseEntity courseObtained = this.serviceAccessBD.findById(idCourse).get();
        Course courseResponse = this.mapper.map(courseObtained, Course.class);
        return courseResponse;
    }

    @Override
    public boolean existsById(long idCourse) {
        return this.serviceAccessBD.findById(idCourse).isPresent();
    }

    @Override
    public List<AcademicSemester> findAllAcademicSemester() {
        List<AcademicSemesterEntity> listObtained = this.serviceAccessBD.findAllAcademicSemesters();
        List<AcademicSemester> listResponse = this.mapper.map(listObtained, new TypeToken<List<AcademicSemester>>(){  
            }.getType());
        return listResponse;
    }

    @Override
    public long existsUser(long idUser) {
        return this.serviceAccessBD.CountByIdUser(idUser);
    }

    @Override
    public Professor findProfessorById(long idProfessor) {
        ProfessorEntity professorObtained = this.serviceAccessBD.findProfessorById(idProfessor);
        Professor professorResponse = this.mapper.map(professorObtained, Professor.class);
        return professorResponse;
    }

    @Override
    public Student findStudentById(long idStudent) {
        StudentEntity studentObtained = this.serviceAccessBD.findStudentById(idStudent);
        Student studentResponse = this.mapper.map(studentObtained, Student.class);
        return studentResponse;
    }

    @Override
    public List<Student> findAllStudents() {
        List<StudentEntity> studentsObtained = this.serviceAccessBD.findAllStundents();
        List<Student> studentResponse = this.mapper.map(studentsObtained, new TypeToken<List<Student>>(){
        }.getType());
        return studentResponse;
    }

    @Override
    public List<Professor> findAllProfessors() {
        List<ProfessorEntity> professorsObtained = this.serviceAccessBD.findAllProfessors();
        List<Professor> professorsResponse = this.mapper.map(professorsObtained, new TypeToken<List<Professor>>(){ 
        }.getType());
        return professorsResponse;
    }
}
