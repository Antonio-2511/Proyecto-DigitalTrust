package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.*;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.FuenteConfiable;

import java.util.List;

/**
 * Clase utilitaria encargada de realizar la conversión (mapping) entre la entidad
 * {@link FuenteConfiable} y sus diferentes DTOs asociados.
 *
 * <p>Centraliza la lógica de transformación entre la capa de persistencia y la capa
 * de presentación, evitando acoplamiento directo entre entidades y DTOs.</p>
 *
 * <p>Incluye métodos para:
 * <ul>
 *     <li>Conversión de entidad a DTO básico</li>
 *     <li>Conversión de entidad a DTO detallado</li>
 *     <li>Conversión de DTO de creación a entidad</li>
 *     <li>Actualización de entidades existentes</li>
 *     <li>Conversión de listas</li>
 * </ul>
 * </p>
 *
 * <p>Todos los métodos son estáticos, ya que la clase no mantiene estado.</p>
 *
 * @author
 */
public class FuenteConfiableMapper {

    /**
     * Convierte una entidad {@link FuenteConfiable} en un {@link FuenteConfiableDTO}.
     *
     * <p>Incluye los datos básicos de la fuente confiable y, si existe relación,
     * los identificadores de las advertencias asociadas.</p>
     *
     * @param entity entidad a convertir
     * @return DTO resultante o null si la entidad es null
     */
    public static FuenteConfiableDTO toDTO(FuenteConfiable entity) {

        if (entity == null) return null;

        FuenteConfiableDTO dto = new FuenteConfiableDTO();

        dto.setIdFuente(entity.getIdFuente());
        dto.setNombreEntidad(entity.getNombreEntidad());
        dto.setTipo(entity.getTipo());
        dto.setTelefono(entity.getTelefono());
        dto.setEmail(entity.getEmail());
        dto.setDominio(entity.getDominio());

        if (entity.getAdvertencia() != null) {
            dto.setAdvertenciasIds(
                    List.of(entity.getAdvertencia().getId())
            );
        }

        return dto;
    }

    /**
     * Convierte una lista de entidades {@link FuenteConfiable} en una lista de {@link FuenteConfiableDTO}.
     *
     * @param entities lista de entidades
     * @return lista de DTOs (vacía si la entrada es null)
     */
    public static List<FuenteConfiableDTO> toDTOList(List<FuenteConfiable> entities) {

        if (entities == null) return List.of();

        return entities
                .stream()
                .map(FuenteConfiableMapper::toDTO)
                .toList();
    }

    /**
     * Convierte una entidad {@link FuenteConfiable} en un {@link FuenteConfiableDetailDTO}.
     *
     * <p>Incluye información completa de la fuente confiable, así como datos
     * relacionados con las advertencias asociadas (IDs y títulos).</p>
     *
     * @param entity entidad a convertir
     * @return DTO detallado o null si la entidad es null
     */
    public static FuenteConfiableDetailDTO toDetailDTO(FuenteConfiable entity) {

        if (entity == null) return null;

        FuenteConfiableDetailDTO dto = new FuenteConfiableDetailDTO();

        dto.setIdFuente(entity.getIdFuente());
        dto.setNombreEntidad(entity.getNombreEntidad());
        dto.setTipo(entity.getTipo());
        dto.setTelefono(entity.getTelefono());
        dto.setEmail(entity.getEmail());
        dto.setDominio(entity.getDominio());

        if (entity.getAdvertencia() != null) {

            dto.setAdvertenciasIds(
                    List.of(entity.getAdvertencia().getId())
            );

            dto.setTitulosAdvertencias(
                    List.of(entity.getAdvertencia().getTitulo())
            );
        }

        return dto;
    }

    /**
     * Convierte un {@link FuenteConfiableCreateDTO} en una entidad {@link FuenteConfiable}.
     *
     * @param dto datos de creación
     * @return entidad creada o null si el DTO es null
     */
    public static FuenteConfiable toEntity(FuenteConfiableCreateDTO dto) {

        if (dto == null) return null;

        FuenteConfiable entity = new FuenteConfiable();

        entity.setNombreEntidad(dto.getNombreEntidad());
        entity.setTipo(dto.getTipo());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());
        entity.setDominio(dto.getDominio());

        return entity;
    }

    /**
     * Copia los datos de un {@link FuenteConfiableUpdateDTO} sobre una entidad existente.
     *
     * <p>Se utiliza en operaciones de actualización para modificar únicamente
     * los campos permitidos sin crear una nueva instancia.</p>
     *
     * @param dto datos de actualización
     * @param entity entidad a modificar
     */
    public static void copyToExistingEntity(FuenteConfiableUpdateDTO dto, FuenteConfiable entity) {

        if (dto == null || entity == null) return;

        entity.setNombreEntidad(dto.getNombreEntidad());
        entity.setTipo(dto.getTipo());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());
        entity.setDominio(dto.getDominio());
    }

    /**
     * Convierte una entidad {@link FuenteConfiable} en un {@link FuenteConfiableUpdateDTO}.
     *
     * <p>Se utiliza para precargar formularios de edición con los datos actuales
     * de la fuente confiable.</p>
     *
     * @param entity entidad origen
     * @return DTO de actualización o null si la entidad es null
     */
    public static FuenteConfiableUpdateDTO toUpdateDTO(FuenteConfiable entity) {

        if (entity == null) return null;

        FuenteConfiableUpdateDTO dto = new FuenteConfiableUpdateDTO();

        dto.setIdFuente(entity.getIdFuente());
        dto.setNombreEntidad(entity.getNombreEntidad());
        dto.setTipo(entity.getTipo());
        dto.setTelefono(entity.getTelefono());
        dto.setEmail(entity.getEmail());
        dto.setDominio(entity.getDominio());

        return dto;
    }
}