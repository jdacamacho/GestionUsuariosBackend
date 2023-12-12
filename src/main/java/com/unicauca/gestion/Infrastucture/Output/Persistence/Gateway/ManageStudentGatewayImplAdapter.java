package com.unicauca.gestion.Infrastucture.Output.Persistence.Gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.unicauca.gestion.Apliccation.Output.ManageStudentGatewayIntPort;
import com.unicauca.gestion.Domain.Models.Student;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.StudentEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories.StudentRepository;

@Service
public class ManageStudentGatewayImplAdapter implements ManageStudentGatewayIntPort{

    private final StudentRepository serviceAccessBD;
    private final ModelMapper mapperStudent;

    public ManageStudentGatewayImplAdapter(StudentRepository serviceAccessBD,
                                          ModelMapper mapperStudent){
        this.serviceAccessBD = serviceAccessBD;
        this.mapperStudent = mapperStudent;
    }

    @Override
    public List<Student> findAll() {
        Iterable<StudentEntity> iterableStudent = this.serviceAccessBD.findAll();
        List<Student> obtainedList = this.mapperStudent.map(iterableStudent,new TypeToken<List<Student>>() {
        }.getType());
        return obtainedList;
    }

    @Override
    public boolean existsById(long idStudent) {
        return this.serviceAccessBD.findById(idStudent).isPresent();
    }

    @Override
    public Student save(Student student) {
        StudentEntity studentToSave = this.mapperStudent.map(student, StudentEntity.class);
        StudentEntity studentSaved = this.serviceAccessBD.save(studentToSave);
        Student studentResponse = this.mapperStudent.map(studentSaved, Student.class);
        return studentResponse;
    }

    @Override
    public Student findById(long idStudent) {
        StudentEntity obtainedStudent = this.serviceAccessBD.findById(idStudent).get();
        Student studentResponse = this.mapperStudent.map(obtainedStudent,Student.class);
        return studentResponse;
    }

    @Override
    public boolean existsByCodeStudentEmailOrUsername(long codeStudent, String email, String username) {
        boolean flagStudent = false;
        StudentEntity obtainedStudent = this.serviceAccessBD.findByCodeStudentOrEmailOrUsername(codeStudent, email, username);
        if(obtainedStudent != null) flagStudent = true;
        return flagStudent;
    }
    
}
