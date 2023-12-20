package com.unicauca.gestion.Infrastucture.Output.Persistence.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "AcademicSemesters")
@Data
@AllArgsConstructor
public class AcademicSemesterEntity {
    @Id
    private long idAcademicSemester;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objAcademicSemester")
    private List<CourseEntity> courses;

    public AcademicSemesterEntity(){
        this.courses = new ArrayList<>();
    }
}
