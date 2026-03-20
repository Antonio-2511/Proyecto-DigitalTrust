package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa un plan de suscripción dentro del sistema.
 * <p>
 * Un plan define las características, beneficios y condiciones económicas
 * asociadas al acceso del usuario a determinadas funcionalidades.
 * </p>
 *
 * <p>
 * Puede estar asociado a múltiples {@link User}, permitiendo gestionar
 * distintos niveles de servicio (por ejemplo: gratuito, premium, etc.).
 * </p>
 */
@Entity
@Table(name = "Plan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    /**
     * Identificador único del plan.
     * <p>
     * Se utiliza el nombre del plan como clave primaria.
     * </p>
     */
    @Id
    @Column(name = "Nombre_plan", length = 45)
    private String nombrePlan;

    /**
     * Descripción de los beneficios incluidos en el plan.
     */
    @Column(name = "Beneficios", length = 100)
    private String beneficios;

    /**
     * Precio del plan.
     */
    @Column(name = "Precio")
    private Double precio;

    /**
     * Fecha de inicio del plan.
     */
    @Column(name = "Fecha_inicio")
    private LocalDateTime fechaInicio;

    /**
     * Fecha de expiración del plan.
     */
    @Column(name = "Fecha_expiracion")
    private LocalDateTime fechaExpiracion;

    /**
     * Lista de usuarios asociados a este plan.
     * <p>
     * Relación uno a muchos: un plan puede tener múltiples usuarios.
     * </p>
     */
    @OneToMany(mappedBy = "plan")
    private List<User> users;
}