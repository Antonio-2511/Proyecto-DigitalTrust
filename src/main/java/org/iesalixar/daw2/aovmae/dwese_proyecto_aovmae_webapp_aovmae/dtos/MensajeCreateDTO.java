package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la creación de un mensaje a analizar.
 *
 * Se utiliza para transferir datos desde la capa de presentación
 * hacia la capa de servicio cuando un usuario envía un mensaje
 * para su análisis (detección de posibles estafas).
 *
 * ⚠️ Contexto:
 * Este DTO es clave en el sistema, ya que representa el input principal
 * del motor de detección de fraude.
 *
 * ⚠️ Seguridad:
 * - Validar contenido para evitar payloads maliciosos.
 * - Limitar tamaño del texto.
 * - Validar referencias (usuario y fuente).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeCreateDTO {

    /**
     * Contenido del mensaje a analizar.
     *
     * Restricciones:
     * - No vacío
     * - Máximo 255 caracteres
     *
     * ⚠️ Seguridad:
     * - Sanitizar si se muestra en vistas (prevención XSS).
     * - Puede contener contenido malicioso.
     */
    @NotBlank
    @Size(max = 255)
    private String contenidoTexto;

    /**
     * Origen del mensaje.
     *
     * Ejemplos: SMS, email, WhatsApp, web.
     *
     * Restricciones:
     * - No vacío
     * - Máximo 45 caracteres
     */
    @NotBlank
    @Size(max = 45)
    private String origen;

    /**
     * Nivel de riesgo detectado.
     *
     * Ejemplos: bajo, medio, alto.
     *
     * ⚠️ Puede ser calculado automáticamente por el sistema.
     */
    @Size(max = 45)
    private String nivelRiesgo;

    /**
     * Resultado del análisis del mensaje.
     *
     * Ej: "posible phishing", "contenido seguro", etc.
     *
     * ⚠️ Normalmente generado por el backend.
     */
    @Size(max = 45)
    private String resultadoAnalisis;

    /**
     * Identificador de la fuente confiable asociada.
     *
     * ⚠️ Seguridad:
     * - Validar que exista en base de datos.
     * - Evitar referencias manipuladas.
     */
    @NotNull
    private Long fuenteConfiableId;

    /**
     * Nombre de usuario asociado al mensaje.
     *
     * ⚠️ Seguridad:
     * - Validar que el usuario exista.
     * - Evitar suplantación (ideal: obtener del contexto de sesión).
     */
    @NotBlank
    private String usernameUsuario;
}