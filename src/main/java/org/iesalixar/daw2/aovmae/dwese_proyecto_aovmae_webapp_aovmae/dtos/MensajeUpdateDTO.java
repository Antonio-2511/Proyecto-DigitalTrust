package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) utilizado para la actualización de mensajes
 * dentro del sistema de detección de estafas online.
 *
 * <p>Este objeto encapsula los datos necesarios para modificar un mensaje existente,
 * aplicando restricciones de validación para garantizar la integridad y consistencia
 * de la información introducida por el usuario.</p>
 *
 * <p>Las anotaciones de validación (Jakarta Validation) permiten asegurar que los
 * campos obligatorios estén presentes y que los valores no excedan los tamaños
 * permitidos definidos en el modelo de datos.</p>
 *
 * <p>Se emplea principalmente en operaciones de tipo UPDATE en controladores REST.</p>
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeUpdateDTO {

    /**
     * Identificador único del mensaje a actualizar.
     * <p>Es obligatorio para poder localizar el registro en el sistema.</p>
     */
    @NotNull
    private Long idMensaje;

    /**
     * Contenido textual del mensaje.
     * <p>No puede estar vacío y tiene una longitud máxima de 255 caracteres.</p>
     */
    @NotBlank
    @Size(max = 255)
    private String contenidoTexto;

    /**
     * Origen del mensaje (por ejemplo: email, SMS, redes sociales).
     * <p>No puede estar vacío y su longitud máxima es de 45 caracteres.</p>
     */
    @NotBlank
    @Size(max = 45)
    private String origen;

    /**
     * Nivel de riesgo asociado al mensaje.
     * <p>Campo opcional con una longitud máxima de 45 caracteres.</p>
     */
    @Size(max = 45)
    private String nivelRiesgo;

    /**
     * Resultado del análisis de ciberseguridad del mensaje.
     * <p>Campo opcional con una longitud máxima de 45 caracteres.</p>
     */
    @Size(max = 45)
    private String resultadoAnalisis;

    /**
     * Identificador de la fuente confiable asociada al mensaje.
     * <p>Este campo es obligatorio para mantener la trazabilidad de la validación.</p>
     */
    @NotNull
    private Long fuenteConfiableId;
}