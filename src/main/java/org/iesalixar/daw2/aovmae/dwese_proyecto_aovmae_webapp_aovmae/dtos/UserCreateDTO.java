package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) utilizado para el registro de nuevos usuarios
 * dentro del sistema de la aplicación de ciberseguridad.
 *
 * <p>Este objeto encapsula los datos necesarios para crear una cuenta de usuario,
 * incluyendo credenciales, información de contacto y el plan asociado.</p>
 *
 * <p>Se aplican validaciones para garantizar que los datos introducidos cumplen
 * con los requisitos mínimos de seguridad e integridad antes de su procesamiento.</p>
 *
 * <p>Se emplea principalmente en operaciones de tipo CREATE en controladores REST.</p>
 *
 * <p><strong>Nota:</strong> La validación de coincidencia entre contraseña y confirmación
 * debe implementarse a nivel de lógica de negocio o mediante un validador personalizado.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    /**
     * Nombre de usuario único.
     * <p>Debe tener entre 3 y 50 caracteres y no puede estar vacío.</p>
     */
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    /**
     * Dirección de correo electrónico del usuario.
     * <p>Campo obligatorio. Se recomienda validar formato con @Email en futuras mejoras.</p>
     */
    @NotBlank
    private String email;

    /**
     * Número de teléfono del usuario.
     * <p>Campo opcional con una longitud máxima de 15 caracteres.</p>
     */
    @Size(max = 15)
    private String telefono;

    /**
     * Contraseña del usuario.
     * <p>Debe tener entre 8 y 100 caracteres. Se recomienda aplicar políticas
     * adicionales de complejidad (mayúsculas, números, caracteres especiales).</p>
     */
    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    /**
     * Confirmación de la contraseña.
     * <p>Campo obligatorio que debe coincidir con la contraseña.</p>
     */
    @NotBlank
    private String confirmPassword;

    /**
     * Identificador del plan asociado al usuario.
     * <p>Campo obligatorio que vincula el usuario con un plan del sistema.</p>
     */
    @NotNull
    private String planId;
}