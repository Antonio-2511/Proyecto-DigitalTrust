package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa la información básica de un mensaje
 * analizado dentro del sistema de detección de estafas online.
 *
 * <p>Se utiliza para transferir datos entre las distintas capas de la aplicación,
 * proporcionando una versión simplificada del mensaje sin incluir información
 * temporal ni datos sensibles adicionales.</p>
 *
 * <p>Contiene los atributos principales necesarios para mostrar resultados de análisis
 * y clasificar el nivel de riesgo asociado al mensaje.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDTO {

    /**
     * Identificador único del mensaje.
     */
    private Long idMensaje;

    /**
     * Contenido textual del mensaje que se analiza en busca de posibles estafas.
     */
    private String contenidoTexto;

    /**
     * Origen del mensaje (por ejemplo: email, SMS, redes sociales, etc.).
     */
    private String origen;

    /**
     * Nivel de riesgo asignado al mensaje tras el análisis
     * (por ejemplo: bajo, medio, alto).
     */
    private String nivelRiesgo;

    /**
     * Resultado del análisis de ciberseguridad aplicado al mensaje.
     */
    private String resultadoAnalisis;

    /**
     * Nombre del usuario que ha proporcionado el mensaje para su análisis.
     */
    private String nombreUsuario;

    /**
     * Nombre de la fuente confiable utilizada para validar la información del mensaje.
     */
    private String nombreFuenteConfiable;
}