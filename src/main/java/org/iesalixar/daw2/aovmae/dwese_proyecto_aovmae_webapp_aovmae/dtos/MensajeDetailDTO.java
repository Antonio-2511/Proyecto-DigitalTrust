package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) que representa el detalle completo de un mensaje
 * analizado dentro de la aplicación de ciberseguridad anti-estafas.
 *
 * <p>Este objeto se utiliza para transportar información entre capas de la aplicación,
 * especialmente desde el backend hacia la capa de presentación, incluyendo datos del
 * análisis de riesgo y del usuario asociado.</p>
 *
 * <p>Incluye información relevante como el contenido del mensaje, su origen,
 * el nivel de riesgo detectado, el resultado del análisis y metadatos del usuario
 * y fuentes confiables.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDetailDTO {

    /**
     * Identificador único del mensaje analizado.
     */
    private Long idMensaje;

    /**
     * Contenido textual del mensaje que ha sido sometido a análisis.
     */
    private String contenidoTexto;

    /**
     * Origen del mensaje (por ejemplo: email, SMS, red social, etc.).
     */
    private String origen;

    /**
     * Nivel de riesgo detectado tras el análisis (por ejemplo: bajo, medio, alto).
     */
    private String nivelRiesgo;

    /**
     * Resultado detallado del análisis de ciberseguridad aplicado al mensaje.
     */
    private String resultadoAnalisis;

    /**
     * Fecha y hora en la que se realizó el análisis del mensaje.
     */
    private LocalDateTime fechaAnalisis;

    /**
     * Nombre del usuario que ha enviado o registrado el mensaje para análisis.
     */
    private String nombreUsuario;

    /**
     * Dirección de correo electrónico del usuario asociado.
     */
    private String emailUsuario;

    /**
     * Nombre de la fuente confiable utilizada para validar o contrastar el mensaje.
     */
    private String nombreFuenteConfiable;
}