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
                .authorizeHttpRequests(auth -> auth


                        // 🔓 PÚBLICAS (CLAVE)
                        .requestMatchers(
                                "/",
                                "/login",
                                "/forgot-password",
                                "/contacto/**",
                                "/reset-password",
                                "/planes",
                                "/css/**",
                                "/images/**",
                                "/api/detector/**"
                        ).permitAll()

                        // 🔒 TODO LO DEMÁS
                        .anyRequest().authenticated()
                )

                // 🔐 LOGIN
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )

                // 🚪 LOGOUT
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}