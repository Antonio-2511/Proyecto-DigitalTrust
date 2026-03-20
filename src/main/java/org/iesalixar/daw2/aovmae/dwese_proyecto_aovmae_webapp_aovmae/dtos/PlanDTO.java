package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa la información básica de un plan
 * dentro del sistema de la aplicación de ciberseguridad.
 *
 * <p>Se utiliza para transferir datos simplificados de un plan entre capas,
 * normalmente en listados o respuestas donde no se requiere información
 * completa como fechas o relaciones con usuarios.</p>
 *
 * <p>Este DTO es útil para optimizar la transferencia de datos y reducir
 * la carga en las respuestas de la API.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO {

    /**
     * Nombre identificativo del plan.
     */
    private String nombrePlan;

    /**
     * Descripción de los beneficios incluidos en el plan.
     */
    private String beneficios;

    /**
     * Precio del plan.
     */
    private Double precio;
}