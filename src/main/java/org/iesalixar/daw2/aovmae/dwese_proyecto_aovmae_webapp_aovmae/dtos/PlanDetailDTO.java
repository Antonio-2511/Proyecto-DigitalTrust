package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO (Data Transfer Object) que representa el detalle completo de un plan
 * dentro del sistema de la aplicación de ciberseguridad.
 *
 * <p>Este objeto se utiliza para transferir información detallada de un plan,
 * incluyendo sus características principales y los usuarios asociados al mismo.</p>
 *
 * <p>Se emplea principalmente en operaciones de consulta (READ), proporcionando
 * una vista completa del plan junto con su relación con los usuarios.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDetailDTO {

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

    /**
     * Fecha y hora de inicio de validez del plan.
     */
    private LocalDateTime fechaInicio;

    /**
     * Fecha y hora de expiración del plan.
     */
    private LocalDateTime fechaExpiracion;

    /**
     * Lista de nombres de usuario asociados a este plan.
     * <p>Representa los usuarios que actualmente tienen contratado este plan.</p>
     */
    private List<String> usernames; // lista de usuarios que tienen este plan
}