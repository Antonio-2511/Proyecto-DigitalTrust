package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) utilizado para la actualización de planes
 * dentro del sistema de la aplicación de ciberseguridad.
 *
 * <p>Este objeto encapsula los datos necesarios para modificar un plan existente,
 * garantizando mediante validaciones que la información proporcionada cumple con
 * las restricciones definidas en el modelo de negocio.</p>
 *
 * <p>Se emplea principalmente en operaciones de tipo UPDATE en controladores REST,
 * asegurando la consistencia de los datos antes de su persistencia.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanUpdateDTO {

    /**
     * Nombre identificativo del plan.
     * <p>Campo obligatorio con una longitud máxima de 45 caracteres.</p>
     */
    @NotBlank
    @Size(max = 45)
    private String nombrePlan;

    /**
     * Descripción de los beneficios del plan.
     * <p>Campo opcional con una longitud máxima de 100 caracteres.</p>
     */
    @Size(max = 100)
    private String beneficios;

    /**
     * Precio del plan.
     * <p>Campo obligatorio que define el coste asociado.</p>
     */
    @NotNull
    private Double precio;

    /**
     * Fecha y hora de inicio del plan.
     * <p>Campo obligatorio que indica cuándo comienza su validez.</p>
     */
    @NotNull
    private LocalDateTime fechaInicio;

    /**
     * Fecha y hora de expiración del plan.
     * <p>Campo obligatorio que determina el fin de su validez.</p>
     */
    @NotNull
    private LocalDateTime fechaExpiracion;
}