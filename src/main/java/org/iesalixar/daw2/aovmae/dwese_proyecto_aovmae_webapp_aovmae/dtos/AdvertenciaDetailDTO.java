package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO de detalle para una advertencia.
 *
 * Se utiliza para mostrar información completa de una advertencia
 * en la capa de presentación (vista o API).
 *
 * A diferencia de los DTO de creación/actualización, este DTO:
 * - Incluye datos derivados o relacionados (usuario, fuentes)
 * - Está orientado a lectura (read-only)
 *
 * ⚠️ Seguridad:
 * - Controlar qué datos se exponen (especialmente email del usuario).
 * - Evitar filtrar información sensible innecesaria.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertenciaDetailDTO {

    /**
     * Identificador único de la advertencia.
     */
    private Long id;

    /**
     * Título de la advertencia.
     */
    private String titulo;

    /**
     * Nivel de criticidad (1–5).
     */
    private Integer nivelCriticidad;

    /**
     * Descripción detallada de la advertencia.
     */
    private String descripcion;

    /**
     * Indica si la advertencia es una emergencia.
     */
    private boolean esEmergencia;

    /**
     * Fecha y hora en la que se envió la advertencia.
     */
    private LocalDateTime fechaEnvio;

    /**
     * Nombre del usuario que generó la advertencia.
     */
    private String nombreUsuario;

    /**
     * Email del usuario asociado.
     *
     * ⚠️ Seguridad:
     * - Considerar anonimización o enmascarado (ej: user***@mail.com)
     *   si se muestra en interfaces públicas.
     */
    private String emailUsuario;

    /**
     * Lista de nombres de fuentes confiables asociadas.
     *
     * Representa relaciones con otras entidades del sistema.
     */
    private List<String> nombresFuentesConfiables;
}