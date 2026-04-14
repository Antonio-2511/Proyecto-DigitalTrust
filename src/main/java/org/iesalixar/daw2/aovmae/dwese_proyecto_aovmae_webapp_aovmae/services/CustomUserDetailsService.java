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

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getContrasenia())
                .authorities(
                        new SimpleGrantedAuthority(user.getRole().getName())
                )
                .build();
    }
}