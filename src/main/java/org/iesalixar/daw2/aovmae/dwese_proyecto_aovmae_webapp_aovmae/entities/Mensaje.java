package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidad que representa un mensaje analizado dentro del sistema.
 * <p>
 * Un mensaje puede provenir de distintas fuentes (email, SMS, web, etc.)
 * y es evaluado para determinar su nivel de riesgo frente a posibles estafas.
 * </p>
 *
 * <p>
 * Está asociado a un {@link User} y opcionalmente vinculado a una
 * {@link FuenteConfiable} para validar su origen.
 * </p>
 */
@Entity
@Table(name = "Mensaje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {

    /**
     * Identificador único del mensaje.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_mensaje")
    private Long idMensaje;

    /**
     * Contenido textual del mensaje analizado.
     */
    @Column(name = "Contenido_texto", length = 255)
    private String contenidoTexto;

    /**
     * Origen del mensaje (por ejemplo: email, SMS, web).
     */
    @Column(name = "Origen", length = 45)
    private String origen;

    /**
     * Nivel de riesgo asignado al mensaje.
     * <p>
     * Ejemplos: SEGURO, BAJO, MEDIO, ALTO.
     * </p>
     */
    @Column(name = "Nivel_riesgo", length = 45)
    private String nivelRiesgo;

    /**
     * Resultado del análisis realizado sobre el mensaje.
     */
    @Column(name = "Resultado_analisis", length = 45)
    private String resultadoAnalisis;

    /**
     * Fecha y hora en la que se realizó el análisis del mensaje.
     */
    @Column(name = "fecha_analisis")
    private LocalDateTime fechaAnalisis;

    /**
     * Usuario asociado al mensaje.
     * <p>
     * Relación muchos a uno: un usuario puede analizar múltiples mensajes.
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
     * Fuente confiable asociada al mensaje.
     * <p>
     * Permite verificar si el mensaje coincide con una entidad legítima
     * o detectar posibles suplantaciones (phishing).
     * </p>
     */
    @ManyToOne
    @JoinColumn(
            name = "Fuente_Confiable_Id_Fuente",
            referencedColumnName = "Id_Fuente",
            nullable = false
    )
    private FuenteConfiable fuenteConfiable;
}