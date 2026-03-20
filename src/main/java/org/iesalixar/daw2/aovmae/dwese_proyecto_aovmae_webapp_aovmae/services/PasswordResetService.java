package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.PasswordResetToken;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.PasswordResetTokenRepository;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Servicio encargado de la gestión del proceso de recuperación de contraseña.
 * <p>
 * Permite generar tokens de recuperación y validar su uso para restablecer
 * contraseñas de usuarios de forma segura.
 * </p>
 *
 * <p>
 * 🔐 Este servicio es crítico en términos de seguridad, ya que gestiona:
 * <ul>
 *     <li>Generación de tokens únicos</li>
 *     <li>Control de expiración</li>
 *     <li>Actualización segura de contraseñas</li>
 * </ul>
 * </p>
 */
@Service
public class PasswordResetService {

    /**
     * Repositorio de tokens de recuperación de contraseña.
     */
    @Autowired
    private PasswordResetTokenRepository tokenRepo;

    /**
     * Repositorio de usuarios.
     */
    @Autowired
    private UserRepository userRepo;

    /**
     * Codificador de contraseñas utilizado para almacenar las contraseñas de forma segura.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Genera un token de recuperación de contraseña para un usuario dado.
     *
     * @param email correo electrónico del usuario
     * @return token generado como {@link String}
     * @throws RuntimeException si el usuario no existe
     */
    public String createToken(String email) {

        /**
         * Búsqueda del usuario por email.
         */
        User user = userRepo.findByGmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        /**
         * Generación de un token único utilizando UUID.
         */
        String token = UUID.randomUUID().toString();

        /**
         * Creación del objeto token con fecha de expiración.
         */
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));

        /**
         * Persistencia del token en base de datos.
         */
        tokenRepo.save(resetToken);

        return token;
    }

    /**
     * Restablece la contraseña de un usuario utilizando un token válido.
     *
     * @param token token de recuperación
     * @param newPassword nueva contraseña en texto plano
     * @throws RuntimeException si el token es inválido o ha expirado
     */
    public void resetPassword(String token, String newPassword) {

        /**
         * Validación de la existencia del token.
         */
        PasswordResetToken resetToken = tokenRepo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        /**
         * Comprobación de expiración del token.
         */
        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        /**
         * Actualización de la contraseña del usuario con cifrado.
         */
        User user = resetToken.getUser();
        user.setContrasenia(passwordEncoder.encode(newPassword));

        userRepo.save(user);

        /**
         * Eliminación del token tras su uso (medida de seguridad).
         */
        tokenRepo.delete(resetToken);
    }
}