package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO de detalle para una fuente confiable.
 *
 * Se utiliza para mostrar información completa de una entidad confiable,
 * incluyendo sus datos de contacto y las advertencias asociadas.
 *
 * A diferencia de DTOs más simples, este incluye:
 * - Relaciones (advertencias)
 * - Información enriquecida (títulos)
 *
 * ⚠️ Seguridad:
 * - Controlar la exposición de datos sensibles (email, teléfono).
 * - Validar que solo usuarios autorizados accedan a esta información.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuenteConfiableDetailDTO {

    /**
     * Identificador único de la fuente confiable.
     */
    private Long idFuente;

    /**
     * Nombre de la entidad.
     */
    private String nombreEntidad;

    /**
     * Tipo de entidad (ej: banco, empresa, organismo público).
     */
    private String tipo;

    /**
     * Teléfono de contacto.
     *
     * ⚠️ Seguridad:
     * Puede ser sensible si se muestra públicamente.
     */
    private String telefono;

    /**
     * Email de contacto.
     *
     * ⚠️ Seguridad:
     * Considerar ocultar parcialmente en interfaces públicas.
     */
    private String email;

    /**
     * Dominio web oficial.
     *
     * ⚠️ Importante para detección de phishing:
     * Puede compararse con dominios sospechosos.
     */
    private String dominio;

    /**
     * Lista de IDs de advertencias asociadas.
     *
     * Representa la relación con advertencias en el sistema.
     */
    // Información detallada de advertencias asociadas
    private List<Long> advertenciasIds;

    /**
     * Lista de títulos de las advertencias asociadas.
     *
     * Permite mostrar información legible sin necesidad de más consultas.
     */
    private List<String> titulosAdvertencias;
}