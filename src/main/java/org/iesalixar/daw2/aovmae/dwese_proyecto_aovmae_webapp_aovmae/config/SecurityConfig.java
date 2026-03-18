package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                //  AUTORIZACIÓN
                .authorizeHttpRequests(auth -> auth

                        // públicas
                        .requestMatchers(
                                "/",
                                "/login",
                                "/css/**",
                                "/images/**",
                                "/?lang=*"
                        ).permitAll()

                        // todo lo demás protegido
                        .anyRequest().authenticated()
                )

                //  LOGIN PERSONALIZADO
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )

                //  LOGOUT
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    //  ENCODER (OBLIGATORIO)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}