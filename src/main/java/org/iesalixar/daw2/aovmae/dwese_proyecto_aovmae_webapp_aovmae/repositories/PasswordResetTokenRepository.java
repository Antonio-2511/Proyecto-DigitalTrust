package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para la gestión de entidades {@link PasswordResetToken}.
 * <p>
 * Extiende de {@link JpaRepository}, proporcionando automáticamente
 * operaciones CRUD básicas (guardar, eliminar, buscar, etc.) sobre la entidad.
 * </p>
 *
 * <p>
 * Se utiliza principalmente para gestionar los tokens de recuperación de contraseña,
 * permitiendo validar solicitudes de reseteo de forma segura.
 * </p>
 */
@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    /**
     * Busca un token de recuperación de contraseña por su valor.
     *
     * @param token cadena única que identifica el token de recuperación
     * @return un {@link Optional} que contiene el {@link PasswordResetToken} si existe,
     *         o vacío en caso contrario
     */
    Optional<PasswordResetToken> findByToken(String token);
}