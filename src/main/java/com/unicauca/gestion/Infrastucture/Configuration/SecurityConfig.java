package com.unicauca.gestion.Infrastucture.Configuration;

import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
        .csrf(csrf ->
            csrf.disable())
        .authorizeHttpRequests(authRequest ->
            authRequest
                .requestMatchers("/apiStudent/students/auth").permitAll()
                .requestMatchers("/apiProfessor/professors/auth").permitAll()
                .anyRequest().authenticated()
                )
            .build();
    }

}
