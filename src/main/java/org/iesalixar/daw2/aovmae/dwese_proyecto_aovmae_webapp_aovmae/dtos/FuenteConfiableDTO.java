package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para la representación básica de una fuente confiable.
 *
 * Se utiliza para:
 * - Listados
 * - Respuestas de API
 * - Transferencia ligera de datos
 *
 * A diferencia del DTO de detalle, este:
 * - No incluye información enriquecida (como títulos de advertencias)
 * - Está optimizado para rendimiento
 *
 * ⚠️ Seguridad:
 * - Evaluar si es necesario exponer email y teléfono.
 * - Aplicar principio de mínimo privilegio en APIs públicas.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuenteConfiableDTO {

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
     * Considerar ocultar o limitar su exposición.
     */
    private String telefono;

    /**
     * Email de contacto.
     *
     * ⚠️ Seguridad:
     * Puede ser sensible en APIs públicas.
     */
    private String email;

    /**
     * Dominio web oficial.
     *
     * ⚠️ Uso clave en ciberseguridad:
     * Permite validar dominios legítimos frente a intentos de phishing.
     */
    private String dominio;

    /**
     * Lista de IDs de advertencias asociadas.
     *
     * Representa la relación con otras entidades del sistema.
     */
    // Relación con advertencias
    private List<Long> advertenciasIds;
}