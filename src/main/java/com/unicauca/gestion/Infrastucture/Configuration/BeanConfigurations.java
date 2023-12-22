package com.unicauca.gestion.Infrastucture.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.unicauca.gestion.Apliccation.Output.ManageStudentGatewayIntPort;
import com.unicauca.gestion.Apliccation.Output.ExceptionFormatterIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageCourseGatewayIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageProfesorGatewayIntPort;
import com.unicauca.gestion.Domain.UseCases.ManageCourseCUImplAdapter;
import com.unicauca.gestion.Domain.UseCases.ManageProfessorCUImplAdapter;
import com.unicauca.gestion.Domain.UseCases.ManageStudentCUImplAdapter;


@Configuration
public class BeanConfigurations {

    @Bean
    public ManageStudentCUImplAdapter createStudentCU(ManageStudentGatewayIntPort gatewayStudent,
                                                        ExceptionFormatterIntPort formatterStudent){
        ManageStudentCUImplAdapter studentCU = new ManageStudentCUImplAdapter(gatewayStudent,formatterStudent);
        return studentCU;
    }

    @Bean
    public ManageProfessorCUImplAdapter createProfessorCU(ManageProfesorGatewayIntPort gatewayProfessor,
                                                            ExceptionFormatterIntPort formatterProfessor){
        ManageProfessorCUImplAdapter professorCU = new ManageProfessorCUImplAdapter(gatewayProfessor, formatterProfessor);
        return professorCU;
    }

    @Bean
    public ManageCourseCUImplAdapter createCourseCU(ManageCourseGatewayIntPort gatewayCourse,
                                                    ExceptionFormatterIntPort formatterCourse){
        ManageCourseCUImplAdapter courseCU = new ManageCourseCUImplAdapter(gatewayCourse, formatterCourse);
        return courseCU;
    }
}
