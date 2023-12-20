package com.unicauca.gestion.Infrastucture.Output.Persistence.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "Courses")
@Data
@AllArgsConstructor
public class CourseEntity {
    @Id
    @Column(unique = true)
    private long codeCourse;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String routeFileDrive;
    
    @ManyToOne
    @JoinColumn(name = "idProfessor")
    private ProfessorEntity objProfessor;

    @ManyToOne
    @JoinColumn(name = "idAcademicSemester")
    private AcademicSemesterEntity objAcademicSemester;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "courses_student",
        joinColumns = @JoinColumn(name = "codeCourse"),
        inverseJoinColumns = @JoinColumn(name = "idStudent",referencedColumnName = "idUser"))
    private List<StudentEntity> students;

    public CourseEntity(){
        this.students = new ArrayList<>();
    }
}
