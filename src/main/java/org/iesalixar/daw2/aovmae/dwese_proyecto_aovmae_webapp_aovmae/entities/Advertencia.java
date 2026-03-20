package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa una advertencia dentro del sistema.
 * <p>
 * Una advertencia recoge información sobre posibles amenazas,
 * fraudes o situaciones de riesgo detectadas en la aplicación.
 * </p>
 *
 * <p>
 * Está relacionada con un {@link User} (quien la genera o gestiona)
 * y puede contener múltiples {@link FuenteConfiable} como referencias.
 * </p>
 */
@Entity
@Table(name = "Advertencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Advertencia {

    /**
     * Identificador único de la advertencia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    /**
     * Título descriptivo de la advertencia.
     */
    @Column(name = "Titulo", length = 100)
    private String titulo;

    /**
     * Nivel de criticidad de la advertencia.
     * <p>
     * Representa la gravedad del riesgo (por ejemplo: 1–5).
     * </p>
     */
    @Column(name = "Nivel_Criticidad")
    private Integer nivelCriticidad;

    /**
     * Descripción detallada de la advertencia.
     */
    @Column(name = "Descripcion", length = 255)
    private String descripcion;

    /**
     * Fecha y hora en la que se envió o generó la advertencia.
     */
    @Column(name = "Fecha_de_envio")
    private LocalDateTime fechaEnvio;

    /**
     * Indica si la advertencia es de tipo emergencia.
     */
    @Column(name = "Es_emergencia")
    private Boolean esEmergencia;

    /**
     * Usuario asociado a la advertencia.
     * <p>
     * Relación muchos a uno: varias advertencias pueden pertenecer a un mismo usuario.
     * </p>
     */
    @ManyToOne
    @JoinColumn(
            name = "users_username",
            referencedColumnName = "username",
            nullable = false
    )
    private User user;

    /**
     * Lista de fuentes confiables asociadas a la advertencia.
     * <p>
     * Relación uno a muchos: una advertencia puede tener múltiples fuentes.
     * </p>
     *
     * <p>
     * - {@link CascadeType#ALL}: las operaciones se propagan a las fuentes.
     * - {@link FetchType#LAZY}: las fuentes se cargan bajo demanda.
     * </p>
     */
    @OneToMany(mappedBy = "advertencia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FuenteConfiable> fuentes;
}