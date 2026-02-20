package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Plan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    @Id
    @Column(name = "Nombre_plan", length = 45)
    private String nombrePlan;

    @Column(name = "Beneficios", length = 100)
    private String beneficios;

    @Column(name = "Precio")
    private Double precio;

    @Column(name = "Fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "Fecha_expiracion")
    private LocalDateTime fechaExpiracion;

    @OneToMany(mappedBy = "plan")
    private List<User> users;
}