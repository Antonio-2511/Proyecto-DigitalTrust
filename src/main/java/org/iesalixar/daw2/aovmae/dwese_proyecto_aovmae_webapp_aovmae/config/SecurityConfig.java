package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.config;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad de la aplicación mediante Spring Security.
 *
 * Esta clase define:
 * - Las rutas públicas y protegidas.
 * - El sistema de autenticación basado en formulario.
 * - La gestión de login y logout.
 * - La integración con la base de datos (usuarios + roles).
 * - El codificador de contraseñas seguro.
 *
 * ⚠️ IMPORTANTE (Ciberseguridad):
 * Esta configuración es crítica en una aplicación anti-estafas, ya que
 * controla el acceso a funcionalidades sensibles y protege los datos del usuario.
 */
@Configuration
@EnableMethodSecurity // 🔐 Permite usar @PreAuthorize en servicios
public class SecurityConfig {

    /**
     * Servicio personalizado que carga usuarios desde la BD.
     */
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Define la cadena de filtros de seguridad principal.
     *
     * Configura:
     * - Autorización de peticiones HTTP.
     * - Sistema de login personalizado.
     * - Sistema de logout.
     * - Integración con UserDetailsService.
     *
     * @param http objeto {@link HttpSecurity} para configurar la seguridad web
     * @return la cadena de filtros de seguridad construida
     * @throws Exception en caso de error en la configuración
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                /**
                 * 🔗 INTEGRACIÓN CON BASE DE DATOS
                 *
                 * Indica a Spring Security que use nuestro servicio personalizado
                 * para cargar usuarios y roles desde la BD.
                 */
                .userDetailsService(userDetailsService)

                .authorizeHttpRequests(auth -> auth

                        /**
                         * 🔓 RUTAS PÚBLICAS
                         *
                         * Estas rutas no requieren autenticación.
                         */
                        .requestMatchers(
                                "/",
                                "/login",
                                "/tienda",
                                "/forgot-password",
                                "/contacto/**",
                                "/reset-password",
                                "/planes",
                                "/css/**",
                                "/images/**",
                                "/api/detector/**"
                        ).permitAll()

                        /**
                         * 🔐 RUTAS POR ROLES
                         *
                         * Control de acceso basado en roles almacenados en BD.
                         *
                         * ⚠️ IMPORTANTE:
                         * hasRole("ADMIN") → busca "ROLE_ADMIN" en la BD
                         */
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/moderador/**").hasRole("MODERATOR")
                        .requestMatchers("/reportes/**").hasAnyRole("USER", "MODERATOR", "ADMIN")

                        /**
                         * 🔒 RESTO DE RUTAS
                         *
                         * Cualquier otra petición requiere autenticación.
                         */
                        .anyRequest().authenticated()
                )

                /**
                 * 🔐 CONFIGURACIÓN DE LOGIN
                 *
                 * - Página personalizada de login.
                 * - Redirección tras login exitoso.
                 */
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )

                /**
                 * 🚪 CONFIGURACIÓN DE LOGOUT
                 *
                 * - Permite cerrar sesión y redirigir al inicio.
                 */
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    /**
     * Define el bean para codificar contraseñas.
     *
     * Se utiliza {@link BCryptPasswordEncoder}, que:
     * - Aplica hashing seguro con salt.
     * - Es resistente a ataques de diccionario y fuerza bruta.
     *
     * ⚠️ NUNCA almacenar contraseñas en texto plano.
     *
     * @return el codificador de contraseñas seguro
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}