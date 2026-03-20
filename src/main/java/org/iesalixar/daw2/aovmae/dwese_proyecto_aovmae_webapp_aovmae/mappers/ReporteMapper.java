package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteUpdateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Reporte;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;

import java.util.List;

/**
 * Clase utilitaria encargada de la conversión (mapping) entre la entidad
 * {@link Reporte} y sus diferentes DTOs asociados.
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
 *     <li>Conversión a DTO de actualización</li>
 *     <li>Actualización de entidades existentes</li>
 * </ul>
 * </p>
 *
 * <p>Todos los métodos son estáticos, ya que la clase no mantiene estado.</p>
 *
 * @author
 */
public class ReporteMapper {

    /**
     * Convierte una entidad {@link Reporte} en un {@link ReporteDTO}.
     *
     * <p>Incluye información básica del reporte, como identificador, título,
     * fecha y nombre del usuario asociado.</p>
     *
     * @param entity entidad a convertir
     * @return DTO resultante o null si la entidad es null
     */
    public static ReporteDTO toDTO(Reporte entity) {
        if (entity == null) return null;

        ReporteDTO dto = new ReporteDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setFechaReporte(entity.getFechaReporte());

        if (entity.getUser() != null) {
            dto.setNombreUsuario(entity.getUser().getUsername());
        }

        return dto;
    }

    /**
     * Convierte una lista de entidades {@link Reporte} en una lista de {@link ReporteDTO}.
     *
     * @param entities lista de entidades
     * @return lista de DTOs (vacía si la entrada es null)
     */
    public static List<ReporteDTO> toDTOList(List<Reporte> entities) {
        if (entities == null) return List.of();
        return entities.stream().map(ReporteMapper::toDTO).toList();
    }

    /**
     * Convierte una entidad {@link Reporte} en un {@link ReporteDetailDTO}.
     *
     * <p>Incluye información completa del reporte, como descripción,
     * fecha y datos del usuario (nombre y email).</p>
     *
     * @param entity entidad a convertir
     * @return DTO detallado o null si la entidad es null
     */
    public static ReporteDetailDTO toDetailDTO(Reporte entity) {
        if (entity == null) return null;

        ReporteDetailDTO dto = new ReporteDetailDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescripcion(entity.getDescripcion());
        dto.setFechaReporte(entity.getFechaReporte());

        if (entity.getUser() != null) {
            dto.setNombreUsuario(entity.getUser().getUsername());
            dto.setEmailUsuario(entity.getUser().getGmail());
        }

        return dto;
    }

    /**
     * Convierte una entidad {@link Reporte} en un {@link ReporteUpdateDTO}.
     *
     * <p>Se utiliza para precargar formularios de edición con los datos actuales del reporte.</p>
     *
     * @param entity entidad origen
     * @return DTO de actualización o null si la entidad es null
     */
    public static ReporteUpdateDTO toUpdateDTO(Reporte entity) {
        if (entity == null) return null;

        ReporteUpdateDTO dto = new ReporteUpdateDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescripcion(entity.getDescripcion());

        return dto;
    }

    /**
     * Convierte un {@link ReporteCreateDTO} en una entidad {@link Reporte}.
     *
     * <p>Asocia el reporte al usuario proporcionado.</p>
     *
     * @param dto datos de creación
     * @param usuario usuario asociado al reporte
     * @return entidad creada o null si el DTO es null
     */
    public static Reporte toEntity(ReporteCreateDTO dto, User usuario) {
        if (dto == null) return null;

        Reporte entity = new Reporte();
        entity.setTitulo(dto.getTitulo());
        entity.setDescripcion(dto.getDescripcion());
        entity.setUser(usuario);

        return entity;
    }

    /**
     * Copia los datos de un {@link ReporteUpdateDTO} sobre una entidad existente.
     *
     * <p>Se utiliza en operaciones de actualización para modificar únicamente
     * los campos permitidos sin crear una nueva instancia.</p>
     *
     * @param dto datos de actualización
     * @param entity entidad a modificar
     */
    public static void copyToExistingEntity(ReporteUpdateDTO dto, Reporte entity) {
        if (dto == null || entity == null) return;

        entity.setTitulo(dto.getTitulo());
        entity.setDescripcion(dto.getDescripcion());
    }
}