package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) utilizado para la creación de reportes
 * dentro del sistema de la aplicación de ciberseguridad.
 *
 * <p>Este objeto encapsula la información necesaria para registrar un nuevo
 * reporte, generalmente asociado a posibles estafas o incidencias detectadas
 * por los usuarios.</p>
 *
 * <p>Incluye validaciones para garantizar que los campos obligatorios estén
 * correctamente informados antes de su procesamiento y almacenamiento.</p>
 *
 * <p>Se emplea principalmente en operaciones de tipo CREATE en controladores REST.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteCreateDTO {

    /**
     * Título del reporte.
     * <p>Campo obligatorio con una longitud máxima de 100 caracteres.</p>
     */
    @NotBlank
    @Size(max = 100)
    private String titulo;

    /**
     * Descripción detallada del reporte.
     * <p>Campo obligatorio que contiene la información completa sobre la incidencia o posible estafa.</p>
     */
    @NotBlank
    private String descripcion;
}