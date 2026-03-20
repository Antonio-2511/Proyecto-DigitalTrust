package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la actualización de una advertencia existente.
 *
 * Se utiliza para transferir datos desde la capa de presentación
 * hacia la capa de servicio cuando se edita una advertencia.
 *
 * Incluye el identificador (id), a diferencia del DTO de creación.
 *
 * ⚠️ Seguridad:
 * - Garantiza que los datos modificados sean válidos.
 * - Evita manipulaciones de campos críticos.
 * - Debe validarse también en el servicio que el recurso exista.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertenciaUpdateDTO {

    /**
     * Identificador único de la advertencia.
     *
     * ⚠️ Seguridad:
     * - No debe ser nulo.
     * - Validar que exista en base de datos antes de actualizar.
     */
    @NotNull
    private Long id;

    /**
     * Título de la advertencia.
     *
     * Restricciones:
     * - No vacío
     * - Máximo 100 caracteres
     */
    @NotBlank
    @Size(max = 100)
    private String titulo;

    /**
     * Nivel de criticidad.
     *
     * Valores permitidos entre 1 y 5.
     */
    @NotNull
    @Min(1)
    @Max(5)
    private Integer nivelCriticidad;

    /**
     * Descripción de la advertencia.
     *
     * ⚠️ Seguridad:
     * - No debe estar vacía.
     * - Sanitizar si se renderiza en HTML (prevención XSS).
     */
    @NotBlank
    private String descripcion;

    /**
     * Indica si la advertencia es una emergencia.
     */
    @NotNull
    private Boolean esEmergencia;

    /**
     * Identificador de la fuente confiable asociada.
     *
     * ⚠️ Seguridad:
     * - Validar existencia en base de datos.
     * - Evitar referencias manipuladas.
     */
    @NotNull
    private Long fuenteConfiableId;
}