package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * - El codificador de contraseñas seguro.
 *
 * ⚠️ IMPORTANTE (Ciberseguridad):
 * Esta configuración es crítica en una aplicación anti-estafas, ya que
 * controla el acceso a funcionalidades sensibles y protege los datos del usuario.
 */
@Configuration
public class SecurityConfig {

    /**
     * Define la cadena de filtros de seguridad principal.
     *
     * Configura:
     * - Autorización de peticiones HTTP.
     * - Sistema de login personalizado.
     * - Sistema de logout.
     *
     * @param http objeto {@link HttpSecurity} para configurar la seguridad web
     * @return la cadena de filtros de seguridad construida
     * @throws Exception en caso de error en la configuración
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        /**
                         * 🔓 RUTAS PÚBLICAS
                         *
                         * Estas rutas no requieren autenticación.
                         * Incluyen recursos estáticos y endpoints necesarios
                         * para el acceso inicial del usuario.
                         *
                         * ⚠️ Revisar periódicamente: cualquier endpoint aquí es accesible
                         * sin login, lo que puede ser vector de ataque si se expone lógica sensible.
                         */
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

                        /**
                         * 🔒 RUTAS PROTEGIDAS
                         *
                         * Cualquier otra petición requiere autenticación.
                         * Esto sigue el principio de "deny by default".
                         */
                        .anyRequest().authenticated()
                )

                /**
                 * 🔐 CONFIGURACIÓN DE LOGIN
                 *
                 * - Página personalizada de login.
                 * - Redirección tras login exitoso.
                 *
                 * ⚠️ Recomendación:
                 * Implementar protección contra ataques de fuerza bruta
                 * (rate limiting, captcha, bloqueo temporal, etc.).
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
                 *
                 * ⚠️ Recomendación:
                 * Invalidar sesión y cookies para evitar session hijacking.
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