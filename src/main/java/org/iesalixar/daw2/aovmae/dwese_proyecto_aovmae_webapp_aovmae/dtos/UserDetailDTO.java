package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) que representa el detalle completo de un usuario
 * dentro del sistema de la aplicación de ciberseguridad.
 *
 * <p>Este objeto se utiliza para transferir información detallada del usuario,
 * incluyendo datos de identificación, contacto, fecha de creación y el plan asociado.</p>
 *
 * <p>Se emplea principalmente en operaciones de consulta (READ), proporcionando
 * una vista completa del perfil del usuario sin incluir información sensible
 * como contraseñas.</p>
 *
 * <p><strong>Nota de seguridad:</strong> Este DTO evita exponer datos críticos
 * como credenciales, siguiendo el principio de mínima exposición de información.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {

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
     * Fecha y hora de creación de la cuenta del usuario.
     */
    private LocalDateTime fechaCreacion;

    /**
     * Nombre del plan asociado al usuario.
     */
    private String planNombre;
}