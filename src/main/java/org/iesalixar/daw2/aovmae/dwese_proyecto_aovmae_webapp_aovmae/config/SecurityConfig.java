package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())          // CSRF desactivado para pruebas
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()          // Todas las rutas accesibles
                )
                .formLogin(login -> login.disable())   // Desactiva login por defecto
                .httpBasic(basic -> basic.disable());  // Desactiva HTTP Basic

        return http.build();
    }
}