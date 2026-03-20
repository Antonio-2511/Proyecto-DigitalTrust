package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) que representa una vista simplificada de un reporte
 * dentro del sistema de la aplicación de ciberseguridad.
 *
 * <p>Se utiliza para transferir información básica de los reportes,
 * generalmente en listados o consultas donde no se requiere el contenido completo
 * de la incidencia.</p>
 *
 * <p>Este DTO permite optimizar el rendimiento de la aplicación al reducir
 * la cantidad de datos transferidos entre capas.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {

    /**
     * Identificador único del reporte.
     */
    private Long id;

    /**
     * Título del reporte.
     */
    private String titulo;

    /**
     * Fecha y hora en la que se creó el reporte.
     */
    private LocalDateTime fechaReporte;

    /**
     * Nombre del usuario que ha creado el reporte.
     */
    private String nombreUsuario;
}