package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa un usuario del sistema.
 * <p>
 * El usuario es el núcleo de la aplicación, ya que interactúa con todas
 * las funcionalidades: análisis de mensajes, generación de reportes,
 * recepción de advertencias y gestión de seguridad.
 * </p>
 *
 * <p>
 * 🔐 Contiene información sensible como credenciales, por lo que debe
 * gestionarse con especial atención a la seguridad (cifrado de contraseñas).
 * </p>
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * Nombre de usuario único que identifica al usuario.
     */
    @Id
    @Column(name = "username", length = 45)
    private String username;

    /**
     * Contraseña del usuario (almacenada cifrada).
     */
    @Column(name = "Contrasenia", length = 100)
    private String contrasenia;

    /**
     * Fecha de creación de la cuenta del usuario.
     */
    @Column(name = "Fecha_creacion")
    private LocalDateTime fechaCreacion;

    /**
     * Número de teléfono del usuario.
     */
    @Column(name = "Telefono", length = 15)
    private String telefono;

    /**
     * Dirección de correo electrónico del usuario.
     */
    @Column(name = "gmail", length = 100)
    private String gmail;

    /**
     * Plan de suscripción asociado al usuario.
     * <p>
     * Relación muchos a uno: múltiples usuarios pueden pertenecer al mismo plan.
     * </p>
     */
    @ManyToOne
    @JoinColumn(
            name = "Plan_Nombre_plan",
            referencedColumnName = "Nombre_plan",
            nullable = false
    )
    private Plan plan;

    /**
     * Lista de advertencias asociadas al usuario.
     * <p>
     * Relación uno a muchos: un usuario puede tener múltiples advertencias.
     * </p>
     */
    @OneToMany(mappedBy = "user")
    private List<Advertencia> advertencias;

    /**
     * Lista de mensajes analizados por el usuario.
     * <p>
     * Relación uno a muchos.
     * </p>
     */
    @OneToMany(mappedBy = "user")
    private List<Mensaje> mensajes;

    /**
     * Lista de reportes generados por el usuario.
     * <p>
     * Relación uno a muchos.
     * </p>
     */
    @OneToMany(mappedBy = "user")
    private List<Reporte> reportes;

    /**
     * Lista de tokens de recuperación de contraseña asociados al usuario.
     * <p>
     * Relación uno a muchos con propagación de operaciones ({@link CascadeType#ALL}).
     * </p>
     *
     * <p>
     * 🔐 Importante: permite gestionar procesos de recuperación de contraseña.
     * </p>
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PasswordResetToken> tokens;
}