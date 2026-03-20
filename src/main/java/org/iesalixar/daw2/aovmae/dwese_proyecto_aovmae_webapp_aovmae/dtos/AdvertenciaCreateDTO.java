package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la creación de una nueva advertencia.
 *
 * Se utiliza para transferir los datos desde la capa de presentación
 * hacia la capa de servicio al crear una advertencia.
 *
 * Incluye validaciones para garantizar la integridad de los datos.
 *
 * ⚠️ Seguridad:
 * - Evita entrada de datos inválidos o maliciosos.
 * - Limita tamaños y rangos.
 * - Obliga a campos críticos a no ser nulos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertenciaCreateDTO {

    /**
     * Título de la advertencia.
     *
     * Restricciones:
     * - No puede estar vacío
     * - Máximo 100 caracteres
     */
    @NotBlank
    @Size(max = 100)
    private String titulo;

    /**
     * Nivel de criticidad de la advertencia.
     *
     * Valores permitidos:
     * - Entre 1 (bajo) y 5 (alto)
     *
     * ⚠️ Importante para priorización de riesgos.
     */
    @NotNull
    @Min(1)
    @Max(5)
    private Integer nivelCriticidad;

    /**
     * Descripción detallada de la advertencia.
     *
     * No puede estar vacía.
     *
     * ⚠️ Recomendación:
     * Aplicar sanitización para evitar XSS si se renderiza en vistas.
     */
    @NotBlank
    private String descripcion;

    /**
     * Indica si la advertencia es de tipo emergencia.
     *
     * - true: requiere atención inmediata
     * - false: advertencia informativa
     */
    @NotNull
    private Boolean esEmergencia;

    /**
     * Identificador de la fuente confiable asociada.
     *
     * ⚠️ Seguridad:
     * - Debe existir en base de datos (validar en servicio).
     * - Evita referencias inválidas o manipuladas.
     */
    @NotNull
    private Long fuenteConfiableId;
}