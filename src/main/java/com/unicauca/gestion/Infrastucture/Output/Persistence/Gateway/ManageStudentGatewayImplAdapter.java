package com.unicauca.gestion.Infrastucture.Output.Persistence.Gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.unicauca.gestion.Apliccation.Output.ManageStudentGatewayIntPort;
import com.unicauca.gestion.Domain.Models.Role;
import com.unicauca.gestion.Domain.Models.Student;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.RoleEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Entities.StudentEntity;
import com.unicauca.gestion.Infrastucture.Output.Persistence.Repositories.StudentRepository;

@Service
public class ManageStudentGatewayImplAdapter implements ManageStudentGatewayIntPort{

    private final StudentRepository serviceAccessBD;
    private final ModelMapper mapper;

    public ManageStudentGatewayImplAdapter(StudentRepository serviceAccessBD,
                                          ModelMapper mapper){
        this.serviceAccessBD = serviceAccessBD;
        this.mapper = mapper;
    }

    @Override
    public List<Student> findAll() {
        Iterable<StudentEntity> iterableStudent = this.serviceAccessBD.findAll();
        List<Student> obtainedList = this.mapper.map(iterableStudent,new TypeToken<List<Student>>() {
        }.getType());
        return obtainedList;
    }

    @Override
    public boolean existsById(long idStudent) {
        return this.serviceAccessBD.findById(idStudent).isPresent();
    }

    @Override
    public Student save(Student student) {
        StudentEntity studentToSave = this.mapper.map(student, StudentEntity.class);
        StudentEntity studentSaved = this.serviceAccessBD.save(studentToSave);
        Student studentResponse = this.mapper.map(studentSaved, Student.class);
        return studentResponse;
    }

    @Override
    public Student findById(long idStudent) {
        StudentEntity obtainedStudent = this.serviceAccessBD.findById(idStudent).get();
        Student studentResponse = this.mapper.map(obtainedStudent,Student.class);
        return studentResponse;
    }

    @Override
    public long existsByIdUserEmailOrUsername(long idUser, String email, String username) {
        return this.serviceAccessBD.countByIdUserOrEmailOrUsername(idUser, email, username);
    }

    @Override
    public List<Role> findAllRoles() {
        List<RoleEntity> roles = this.serviceAccessBD.findAllRoles();
        List<Role> rolesResponse = this.mapper.map(roles, new TypeToken<List<Role>>(){    
        }.getType());
        return rolesResponse;
    }

    @Override
    public boolean existByCodeStudent(long codeStudent) {
        boolean flagResponse = false;
        StudentEntity student = this.serviceAccessBD.findByCodeStudent(codeStudent);
        if(student != null) flagResponse = true;
        return flagResponse;
    }
    
}
