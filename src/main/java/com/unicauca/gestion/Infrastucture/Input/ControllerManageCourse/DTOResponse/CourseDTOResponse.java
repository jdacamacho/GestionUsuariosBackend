package com.unicauca.gestion.Infrastucture.Input.ControllerManageCourse.DTOResponse;

import com.unicauca.gestion.Infrastucture.Input.ControllerManageProfessor.DTOResponse.ProfessorDTOResponse;
import com.unicauca.gestion.Infrastucture.Input.ControllerManageStudent.DTOResponse.StudentDTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CourseDTOResponse {
    private long codeCourse;
    private String name;
    private String routeFileDrive;
    private ProfessorDTOResponse objProfessor;
    private AcademicSemesterDTOResponse objAcademicSemester;
    private List<StudentDTOResponse> students;

    public CourseDTOResponse(){
        this.students = new ArrayList<>();
    }
}
