package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa una vista simplificada de un usuario
 * dentro del sistema de la aplicación de ciberseguridad.
 *
 * <p>Se utiliza para transferir información básica del usuario en listados
 * o consultas donde no se requiere información detallada como fechas o datos adicionales.</p>
 *
 * <p>Este DTO permite optimizar el rendimiento al reducir la cantidad de datos
 * transferidos entre las distintas capas de la aplicación.</p>
 *
 * <p><strong>Nota de seguridad:</strong> No incluye información sensible como
 * contraseñas, cumpliendo con el principio de mínima exposición de datos.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    /**
     * Identificador único del usuario.
     */
    private Long id;

    /**
     * Nombre de usuario.
     */
    private String username;

    /**
     * Dirección de correo electrónico del usuario.
     */
    private String email;

    /**
     * Número de teléfono del usuario.
     */
    private String telefono;

    /**
     * Nombre del plan asociado al usuario.
     */
    private String planNombre;
}