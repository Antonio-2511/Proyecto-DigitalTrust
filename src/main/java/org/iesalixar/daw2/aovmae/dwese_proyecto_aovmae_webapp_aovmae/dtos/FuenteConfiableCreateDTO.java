package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para la creación de una fuente confiable.
 *
 * Se utiliza para transferir datos desde la capa de presentación
 * hacia la capa de servicio al registrar una entidad confiable.
 *
 * ⚠️ Contexto:
 * Las fuentes confiables son fundamentales en sistemas anti-estafa,
 * ya que sirven como referencia para validar información.
 *
 * ⚠️ Seguridad:
 * - Validar correctamente los datos de contacto.
 * - Evitar introducir entidades falsas o manipuladas.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuenteConfiableCreateDTO {

    /**
     * Nombre de la entidad confiable.
     *
     * Restricciones:
     * - No vacío
     * - Máximo 150 caracteres
     */
    @NotBlank
    @Size(max = 150)
    private String nombreEntidad;

    /**
     * Tipo de entidad (ej: banco, organismo público, empresa, etc.).
     *
     * Restricciones:
     * - No vacío
     * - Máximo 50 caracteres
     */
    @NotBlank
    @Size(max = 50)
    private String tipo;

    /**
     * Número de teléfono de la entidad.
     *
     * ⚠️ Seguridad:
     * - Validar formato si se usa en producción (regex recomendado).
     * - Evitar números falsos o maliciosos.
     */
    @Size(max = 15)
    private String telefono;

    /**
     * Correo electrónico de contacto.
     *
     * Restricciones:
     * - Debe cumplir formato email válido
     * - Máximo 150 caracteres
     */
    @Email
    @Size(max = 150)
    private String email;

    /**
     * Dominio web oficial de la entidad.
     *
     * ⚠️ Seguridad:
     * - Validar que sea un dominio legítimo.
     * - Puede utilizarse para detectar phishing.
     */
    @Size(max = 150)
    private String dominio;

    /**
     * Lista de IDs de advertencias asociadas.
     *
     * Asociación opcional.
     *
     * ⚠️ Seguridad:
     * - Validar que los IDs existan en base de datos.
     * - Evitar referencias manipuladas.
     */
    // Asociación opcional con advertencias
    private List<Long> advertenciasIds;
}