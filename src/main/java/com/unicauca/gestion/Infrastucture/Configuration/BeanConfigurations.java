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
import com.unicauca.gestion.Infrastucture.JWT.JwtService;


@Configuration
public class BeanConfigurations {

    @Bean
    public ManageStudentCUImplAdapter createStudentCU(ManageStudentGatewayIntPort gatewayStudent,
                                                        ExceptionFormatterIntPort formatterStudent,
                                                        JwtService jwtService,PasswordEncoder passwordEncoder,
                                                        AuthenticationManager authenticationManager){
        ManageStudentCUImplAdapter studentCU = new ManageStudentCUImplAdapter(gatewayStudent,formatterStudent,
                                                                            jwtService,passwordEncoder,authenticationManager);
        return studentCU;
    }

    @Bean
    public ManageProfessorCUImplAdapter createProfessorCU(ManageProfesorGatewayIntPort gatewayProfessor,
                                                            ExceptionFormatterIntPort formatterProfessor,
                                                            JwtService jwtService,PasswordEncoder passwordEncoder,
                                                            AuthenticationManager authenticationManager){
        ManageProfessorCUImplAdapter professorCU = new ManageProfessorCUImplAdapter(gatewayProfessor, formatterProfessor,
                                                                                jwtService,passwordEncoder,authenticationManager);
        return professorCU;
    }

    @Bean
    public ManageCourseCUImplAdapter createCourseCU(ManageCourseGatewayIntPort gatewayCourse,
                                                    ExceptionFormatterIntPort formatterCourse){
        ManageCourseCUImplAdapter courseCU = new ManageCourseCUImplAdapter(gatewayCourse, formatterCourse);
        return courseCU;
    }
}
