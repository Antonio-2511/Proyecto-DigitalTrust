package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.*;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.FuenteConfiable;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Mensaje;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;

import java.util.List;

/**
 * Clase utilitaria encargada de la conversión (mapping) entre la entidad
 * {@link Mensaje} y sus diferentes DTOs asociados.
 *
 * <p>Centraliza la lógica de transformación entre la capa de persistencia
 * y la capa de presentación, evitando el acoplamiento directo entre entidades
 * y DTOs.</p>
 *
 * <p>Incluye métodos para:
 * <ul>
 *     <li>Conversión de entidad a DTO básico</li>
 *     <li>Conversión de entidad a DTO detallado</li>
 *     <li>Conversión de listas</li>
 *     <li>Conversión de DTO de creación a entidad</li>
 *     <li>Actualización de entidades existentes</li>
 * </ul>
 * </p>
 *
 * <p>Todos los métodos son estáticos, ya que la clase no mantiene estado.</p>
 *
 * @author
 */
public class MensajeMapper {

    /**
     * Convierte una entidad {@link Mensaje} en un {@link MensajeDTO}.
     *
     * <p>Incluye información básica del mensaje, así como el nombre del usuario
     * y de la fuente confiable asociada.</p>
     *
     * @param entity entidad a convertir
     * @return DTO resultante o null si la entidad es null
     */
    public static MensajeDTO toDTO(Mensaje entity) {
        if (entity == null) return null;

        MensajeDTO dto = new MensajeDTO();
        dto.setIdMensaje(entity.getIdMensaje());
        dto.setContenidoTexto(entity.getContenidoTexto());
        dto.setOrigen(entity.getOrigen());
        dto.setNivelRiesgo(entity.getNivelRiesgo());
        dto.setResultadoAnalisis(entity.getResultadoAnalisis());

        if (entity.getUser() != null) {
            dto.setNombreUsuario(entity.getUser().getUsername());
        }
        if (entity.getFuenteConfiable() != null) {
            dto.setNombreFuenteConfiable(entity.getFuenteConfiable().getNombreEntidad());
        }

        return dto;
    }

    /**
     * Convierte una lista de entidades {@link Mensaje} en una lista de {@link MensajeDTO}.
     *
     * @param entities lista de entidades
     * @return lista de DTOs (vacía si la entrada es null)
     */
    public static List<MensajeDTO> toDTOList(List<Mensaje> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .map(MensajeMapper::toDTO)
                .toList();
    }

    /**
     * Convierte una entidad {@link Mensaje} en un {@link MensajeDetailDTO}.
     *
     * <p>Incluye información completa del mensaje, como la fecha de análisis,
     * datos del usuario (nombre y email) y la fuente confiable asociada.</p>
     *
     * @param entity entidad a convertir
     * @return DTO detallado o null si la entidad es null
     */
    public static MensajeDetailDTO toDetailDTO(Mensaje entity) {
        if (entity == null) return null;

        MensajeDetailDTO dto = new MensajeDetailDTO();
        dto.setIdMensaje(entity.getIdMensaje());
        dto.setContenidoTexto(entity.getContenidoTexto());
        dto.setOrigen(entity.getOrigen());
        dto.setNivelRiesgo(entity.getNivelRiesgo());
        dto.setResultadoAnalisis(entity.getResultadoAnalisis());
        dto.setFechaAnalisis(entity.getFechaAnalisis());

        if (entity.getUser() != null) {
            dto.setNombreUsuario(entity.getUser().getUsername());
            dto.setEmailUsuario(entity.getUser().getGmail());
        }
        if (entity.getFuenteConfiable() != null) {
            dto.setNombreFuenteConfiable(entity.getFuenteConfiable().getNombreEntidad());
        }

        return dto;
    }

    /**
     * Convierte un {@link MensajeCreateDTO} en una entidad {@link Mensaje}.
     *
     * <p>Asocia el mensaje con el usuario y la fuente confiable proporcionados.</p>
     *
     * @param dto datos de creación
     * @param usuario usuario asociado al mensaje
     * @param fuenteConfiable fuente confiable asociada
     * @return entidad creada o null si el DTO es null
     */
    public static Mensaje toEntity(
            MensajeCreateDTO dto,
            User usuario,
            FuenteConfiable fuenteConfiable) {

        if (dto == null) return null;

        Mensaje entity = new Mensaje();
        entity.setContenidoTexto(dto.getContenidoTexto());
        entity.setOrigen(dto.getOrigen());
        entity.setNivelRiesgo(dto.getNivelRiesgo());
        entity.setResultadoAnalisis(dto.getResultadoAnalisis());
        entity.setUser(usuario);
        entity.setFuenteConfiable(fuenteConfiable);

        return entity;
    }

    /**
     * Copia los datos de un {@link MensajeUpdateDTO} sobre una entidad existente.
     *
     * <p>Se utiliza en operaciones de actualización para modificar únicamente
     * los campos permitidos sin crear una nueva instancia.</p>
     *
     * @param dto datos de actualización
     * @param entity entidad a modificar
     * @param fuenteConfiable nueva fuente confiable asociada
     */
    public static void copyToExistingEntity(
            MensajeUpdateDTO dto,
            Mensaje entity,
            FuenteConfiable fuenteConfiable) {

        if (dto == null || entity == null) return;

        entity.setContenidoTexto(dto.getContenidoTexto());
        entity.setOrigen(dto.getOrigen());
        entity.setNivelRiesgo(dto.getNivelRiesgo());
        entity.setResultadoAnalisis(dto.getResultadoAnalisis());
        entity.setFuenteConfiable(fuenteConfiable);
    }
}