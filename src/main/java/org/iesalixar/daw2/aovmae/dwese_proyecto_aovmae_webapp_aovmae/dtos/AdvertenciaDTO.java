package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para la representación resumida de una advertencia.
 *
 * Se utiliza principalmente para:
 * - Listados
 * - Respuestas de APIs
 * - Vistas donde no se requiere el detalle completo
 *
 * A diferencia de {@code AdvertenciaDetailDTO}, este DTO:
 * - Contiene solo información esencial
 * - Reduce la cantidad de datos transferidos
 *
 * ⚠️ Seguridad:
 * - Minimiza la exposición de datos sensibles (no incluye email, descripción, etc.).
 * - Sigue el principio de "mínimo privilegio de información".
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertenciaDTO {

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
     *
     * Permite priorizar visualmente las advertencias.
     */
    private Integer nivelCriticidad;

    /**
     * Indica si la advertencia es de tipo emergencia.
     */
    private boolean esEmergencia;

    /**
     * Fecha y hora de envío de la advertencia.
     */
    private LocalDateTime fechaEnvio;

    /**
     * Nombre del usuario que generó la advertencia.
     *
     * ⚠️ Seguridad:
     * Evitar incluir identificadores sensibles innecesarios.
     */
    private String nombreUsuario;

    /**
     * Lista de nombres de fuentes confiables asociadas.
     *
     * Representa relaciones simplificadas para visualización.
     */
    private List<String> nombresFuentesConfiables;
}