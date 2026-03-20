package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) utilizado para la actualización de usuarios
 * dentro del sistema de la aplicación de ciberseguridad.
 *
 * <p>Este objeto encapsula los datos necesarios para modificar la información
 * de un usuario existente, incluyendo datos personales y la posibilidad de
 * cambiar la contraseña de forma controlada.</p>
 *
 * <p>Se aplican validaciones para garantizar la integridad de los datos,
 * y se introduce un mecanismo opcional para la actualización de credenciales.</p>
 *
 * <p>Se emplea principalmente en operaciones de tipo UPDATE en controladores REST.</p>
 *
 * <p><strong>Nota:</strong> La validación de coincidencia entre contraseña y confirmación,
 * así como la obligatoriedad de la contraseña cuando {@code cambiarPassword} es true,
 * debe implementarse a nivel de lógica de negocio o mediante validadores personalizados.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

    /**
     * Nombre de usuario.
     * <p>Campo obligatorio con una longitud entre 3 y 50 caracteres.</p>
     */
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    /**
     * Dirección de correo electrónico del usuario.
     * <p>Campo obligatorio. Se recomienda validar el formato con @Email como mejora.</p>
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
     * Identificador del plan asociado al usuario.
     * <p>Campo obligatorio que define la relación con el plan contratado.</p>
     */
    @NotNull
    private String planId;

    /**
     * Indicador que determina si se debe realizar un cambio de contraseña.
     * <p>Por defecto es false. Si es true, los campos password y confirmPassword
     * deben ser validados.</p>
     */
    private Boolean cambiarPassword = false;

    /**
     * Nueva contraseña del usuario.
     * <p>Campo opcional, pero obligatorio si {@code cambiarPassword} es true.
     * Debe tener entre 8 y 100 caracteres.</p>
     */
    @Size(min = 8, max = 100)
    private String password;

    /**
     * Confirmación de la nueva contraseña.
     * <p>Debe coincidir con el campo password cuando se realiza un cambio de contraseña.</p>
     */
    private String confirmPassword;
}