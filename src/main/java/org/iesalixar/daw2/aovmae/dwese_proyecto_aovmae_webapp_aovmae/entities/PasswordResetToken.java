package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidad que representa un token de recuperación de contraseña.
 * <p>
 * Este token se utiliza para permitir a un usuario restablecer su contraseña
 * de forma segura mediante un enlace temporal.
 * </p>
 *
 * <p>
 * 🔐 Es un componente crítico de seguridad que incluye:
 * <ul>
 *     <li>Un identificador único (token)</li>
 *     <li>Una fecha de expiración</li>
 *     <li>Asociación con un usuario</li>
 * </ul>
 * </p>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken {

    /**
     * Identificador único del token.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Valor del token utilizado para validar la solicitud de recuperación.
     * <p>
     * Generalmente generado mediante UUID para garantizar unicidad.
     * </p>
     */
    private String token;

    /**
     * Fecha y hora de expiración del token.
     * <p>
     * Una vez superada, el token deja de ser válido.
     * </p>
     */
    private LocalDateTime expiryDate;

    /**
     * Usuario asociado al token.
     * <p>
     * Relación muchos a uno: un usuario puede tener múltiples tokens activos
     * (aunque en sistemas seguros suele limitarse a uno activo).
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
}