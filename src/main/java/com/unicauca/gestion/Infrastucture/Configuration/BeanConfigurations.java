package com.unicauca.gestion.Infrastucture.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.unicauca.gestion.Apliccation.Output.ManageStudentGatewayIntPort;
import com.unicauca.gestion.Apliccation.Output.ExceptionFormatterIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageAuthGatewayIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageCourseGatewayIntPort;
import com.unicauca.gestion.Apliccation.Output.ManageProfesorGatewayIntPort;
import com.unicauca.gestion.Domain.UseCases.ManageAuthCUImplAdapter;
import com.unicauca.gestion.Domain.UseCases.ManageCourseCUImplAdapter;
import com.unicauca.gestion.Domain.UseCases.ManageProfessorCUImplAdapter;
import com.unicauca.gestion.Domain.UseCases.ManageStudentCUImplAdapter;
import com.unicauca.gestion.Infrastucture.JWT.JwtService;


@Configuration
public class BeanConfigurations {

    @Bean
    public ManageStudentCUImplAdapter createStudentCU(ManageStudentGatewayIntPort gatewayStudent,
                                                        ExceptionFormatterIntPort formatterStudent,
                                                        PasswordEncoder passwordEncoder){
        ManageStudentCUImplAdapter studentCU = new ManageStudentCUImplAdapter(gatewayStudent,
                                                                            formatterStudent,
                                                                            passwordEncoder);
        return studentCU;
    }

    @Bean
    public ManageProfessorCUImplAdapter createProfessorCU(ManageProfesorGatewayIntPort gatewayProfessor,
                                                            ExceptionFormatterIntPort formatterProfessor,
                                                            PasswordEncoder passwordEncoder){
        ManageProfessorCUImplAdapter professorCU = new ManageProfessorCUImplAdapter(gatewayProfessor,
                                                                                formatterProfessor,
                                                                                passwordEncoder);
        return professorCU;
    }

    @Bean
    public ManageCourseCUImplAdapter createCourseCU(ManageCourseGatewayIntPort gatewayCourse,
                                                    ExceptionFormatterIntPort formatterCourse){
        ManageCourseCUImplAdapter courseCU = new ManageCourseCUImplAdapter(gatewayCourse, formatterCourse);
        return courseCU;
    }

    @Bean
    public ManageAuthCUImplAdapter createAuthCU(ManageAuthGatewayIntPort gatewayAuth,
                                                JwtService jwtService,AuthenticationManager authenticationManager){
        ManageAuthCUImplAdapter authCU = new ManageAuthCUImplAdapter(gatewayAuth, jwtService, authenticationManager);
        return authCU;
    }
}
