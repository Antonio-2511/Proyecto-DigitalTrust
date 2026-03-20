package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) utilizado para la actualización de reportes
 * dentro del sistema de la aplicación de ciberseguridad.
 *
 * <p>Este objeto encapsula los datos necesarios para modificar un reporte existente,
 * incluyendo su identificador, título y descripción.</p>
 *
 * <p>Se aplican validaciones para garantizar la integridad de los datos antes
 * de ser procesados, evitando inconsistencias en la persistencia.</p>
 *
 * <p>Se emplea principalmente en operaciones de tipo UPDATE en controladores REST.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteUpdateDTO {

    /**
     * Identificador único del reporte a actualizar.
     * <p>Campo obligatorio para localizar el registro en el sistema.</p>
     */
    @NotNull
    private Long id;

    /**
     * Título del reporte.
     * <p>Campo obligatorio con una longitud máxima de 100 caracteres.</p>
     */
    @NotBlank
    @Size(max = 100)
    private String titulo;

    /**
     * Descripción detallada del reporte.
     * <p>Campo obligatorio que contiene la información completa de la incidencia o posible estafa.</p>
     */
    @NotBlank
    private String descripcion;
}