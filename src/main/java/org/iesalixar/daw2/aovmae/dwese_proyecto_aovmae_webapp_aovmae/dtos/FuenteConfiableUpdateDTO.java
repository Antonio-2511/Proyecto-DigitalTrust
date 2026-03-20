package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para la actualización de una fuente confiable.
 *
 * Se utiliza para modificar entidades existentes en el sistema.
 * Incluye el identificador (idFuente) para localizar el recurso.
 *
 * ⚠️ Contexto:
 * Las fuentes confiables son clave en la detección de estafas,
 * por lo que cualquier modificación debe ser validada cuidadosamente.
 *
 * ⚠️ Seguridad:
 * - Validar existencia del recurso antes de actualizar.
 * - Evitar manipulaciones de relaciones (advertenciasIds).
 * - Controlar permisos (solo usuarios autorizados).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuenteConfiableUpdateDTO {

    /**
     * Identificador único de la fuente confiable.
     *
     * ⚠️ Seguridad:
     * - Obligatorio.
     * - Debe existir en base de datos.
     */
    @NotNull
    private Long idFuente;

    /**
     * Nombre de la entidad.
     *
     * Restricciones:
     * - No vacío
     * - Máximo 150 caracteres
     */
    @NotBlank
    @Size(max = 150)
    private String nombreEntidad;

    /**
     * Tipo de entidad.
     *
     * Ej: banco, empresa, organismo público.
     */
    @NotBlank
    @Size(max = 50)
    private String tipo;

    /**
     * Teléfono de contacto.
     *
     * ⚠️ Seguridad:
     * - Validar formato en producción.
     * - Evitar datos falsos o maliciosos.
     */
    @Size(max = 15)
    private String telefono;

    /**
     * Email de contacto.
     *
     * Restricciones:
     * - Formato válido de email
     * - Máximo 150 caracteres
     */
    @Email
    @Size(max = 150)
    private String email;

    /**
     * Dominio web oficial.
     *
     * ⚠️ Seguridad:
     * - Validar que el dominio sea legítimo.
     * - Puede usarse para detección de phishing.
     */
    @Size(max = 150)
    private String dominio;

    /**
     * Lista de IDs de advertencias asociadas.
     *
     * ⚠️ Seguridad:
     * - Validar que existan.
     * - Evitar manipulación de relaciones.
     */
    private List<Long> advertenciasIds;
}