package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) que representa el detalle completo de un reporte
 * dentro del sistema de la aplicación de ciberseguridad.
 *
 * <p>Este objeto se utiliza para transferir información detallada de un reporte,
 * incluyendo su contenido, la fecha de creación y los datos del usuario que lo ha generado.</p>
 *
 * <p>Se emplea principalmente en operaciones de consulta (READ), proporcionando
 * una visión completa de la incidencia o posible estafa reportada.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDetailDTO {

    /**
     * Identificador único del reporte.
     */
    private Long id;

    /**
     * Título del reporte.
     */
    private String titulo;

    /**
     * Descripción detallada del reporte.
     */
    private String descripcion;

    /**
     * Fecha y hora en la que se creó el reporte.
     */
    private LocalDateTime fechaReporte;

    /**
     * Nombre del usuario que ha creado el reporte.
     */
    private String nombreUsuario;

    /**
     * Dirección de correo electrónico del usuario asociado al reporte.
     */
    private String emailUsuario;
}