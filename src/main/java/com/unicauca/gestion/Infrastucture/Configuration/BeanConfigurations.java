package com.unicauca.gestion.Infrastucture.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.unicauca.gestion.Apliccation.Output.ManageStudentGatewayIntPort;
import com.unicauca.gestion.Domain.UseCases.ManageStudentCUImplAdapter;

@Configuration
public class BeanConfigurations {

    @Bean
    public ManageStudentCUImplAdapter createStudentCU(ManageStudentGatewayIntPort gatewayStudent){
        ManageStudentCUImplAdapter studentCU = new ManageStudentCUImplAdapter(gatewayStudent);
        return studentCU;
    }
}
