package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidad que representa un reporte generado por un usuario.
 * <p>
 * Un reporte recoge información sobre posibles incidentes, fraudes
 * o situaciones sospechosas detectadas por los usuarios del sistema.
 * </p>
 *
 * <p>
 * Es una pieza clave en el sistema antiestafas, ya que permite
 * recopilar información real para su análisis y posible generación
 * de advertencias.
 * </p>
 */
@Entity
@Table(name = "Reporte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {

    /**
     * Identificador único del reporte.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_reporte")
    private Long id;

    /**
     * Título descriptivo del reporte.
     */
    @Column(name = "Titulo", length = 100)
    private String titulo;

    /**
     * Descripción detallada del incidente o situación reportada.
     */
    @Column(name = "Descripcion", length = 255)
    private String descripcion;

    /**
     * Fecha y hora en la que se realizó el reporte.
     */
    @Column(name = "Fecha_reporte")
    private LocalDateTime fechaReporte;

    /**
     * Usuario que ha generado el reporte.
     * <p>
     * Relación muchos a uno: un usuario puede generar múltiples reportes.
     * </p>
     */
    @ManyToOne
    @JoinColumn(
            name = "users_username",
            referencedColumnName = "username",
            nullable = false
    )
    private User user;
}