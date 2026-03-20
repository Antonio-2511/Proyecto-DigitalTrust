package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) utilizado para la creación de nuevos planes
 * dentro del sistema de la aplicación de ciberseguridad.
 *
 * <p>Este objeto encapsula los datos necesarios para registrar un plan,
 * incluyendo su nombre, beneficios asociados, precio y periodo de validez.</p>
 *
 * <p>Se aplican restricciones de validación para garantizar que los datos
 * introducidos cumplan con los requisitos definidos, evitando inconsistencias
 * en la persistencia.</p>
 *
 * <p>Este DTO se emplea principalmente en operaciones de tipo CREATE
 * en controladores REST.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanCreateDTO {

    /**
     * Nombre identificativo del plan.
     * <p>Campo obligatorio con una longitud máxima de 45 caracteres.</p>
     */
    @NotBlank
    @Size(max = 45)
    private String nombrePlan;

    /**
     * Descripción de los beneficios incluidos en el plan.
     * <p>Campo opcional con una longitud máxima de 100 caracteres.</p>
     */
    @Size(max = 100)
    private String beneficios;

    /**
     * Precio del plan.
     * <p>Campo obligatorio que define el coste asociado al plan.</p>
     */
    @NotNull
    private Double precio;

    /**
     * Fecha y hora de inicio de validez del plan.
     * <p>Campo obligatorio que indica cuándo comienza a estar activo.</p>
     */
    @NotNull
    private LocalDateTime fechaInicio;

    /**
     * Fecha y hora de expiración del plan.
     * <p>Campo obligatorio que determina el fin de la validez del plan.</p>
     */
    @NotNull
    private LocalDateTime fechaExpiracion;
}