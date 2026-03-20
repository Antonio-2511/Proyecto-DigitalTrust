package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Implementación personalizada de {@link UserDetailsService} utilizada por Spring Security.
 * <p>
 * Esta clase se encarga de cargar los datos del usuario desde la base de datos
 * durante el proceso de autenticación.
 * </p>
 *
 * <p>
 * Convierte la entidad {@link User} en un objeto {@link UserDetails},
 * que es el formato requerido por Spring Security para gestionar la autenticación.
 * </p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Repositorio de acceso a datos para la entidad {@link User}.
     */
    private final UserRepository userRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param userRepository repositorio de usuarios
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Carga un usuario por su nombre de usuario.
     * <p>
     * Este método es invocado automáticamente por Spring Security durante
     * el proceso de autenticación.
     * </p>
     *
     * @param username nombre de usuario introducido en el login
     * @return objeto {@link UserDetails} con la información del usuario
     * @throws UsernameNotFoundException si el usuario no existe en la base de datos
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /**
         * Búsqueda del usuario en base de datos.
         */
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        /**
         * Construcción del objeto UserDetails requerido por Spring Security.
         * <p>
         * Se asigna el rol "USER". Spring automáticamente añadirá el prefijo "ROLE_".
         * </p>
         */
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getContrasenia())
                .roles("USER") //  importante: sin ROLE_
                .build();
    }
}