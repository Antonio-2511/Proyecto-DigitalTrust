package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaUpdateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Advertencia;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;

import java.util.List;

/**
 * Clase utilitaria encargada de realizar la conversión (mapping) entre la entidad
 * {@link Advertencia} y sus distintos DTOs asociados.
 *
 * <p>Centraliza la lógica de transformación entre capas, evitando acoplamiento
 * entre la capa de persistencia (entidades) y la capa de presentación (DTOs).</p>
 *
 * <p>Incluye métodos para:
 * <ul>
 *     <li>Conversión de entidad a DTO simplificado</li>
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
public class AdvertenciaMapper {

    /**
     * Convierte una entidad {@link Advertencia} en un {@link AdvertenciaDTO}.
     *
     * <p>Incluye información básica de la advertencia, así como el nombre del usuario
     * y los nombres de las fuentes confiables asociadas.</p>
     *
     * @param entity entidad a convertir
     * @return DTO resultante o null si la entidad es null
     */
    public static AdvertenciaDTO toDTO(Advertencia entity) {

        if (entity == null) return null;

        AdvertenciaDTO dto = new AdvertenciaDTO();

        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setNivelCriticidad(entity.getNivelCriticidad());
        dto.setEsEmergencia(entity.getEsEmergencia());
        dto.setFechaEnvio(entity.getFechaEnvio());

        if (entity.getUser() != null) {
            dto.setNombreUsuario(entity.getUser().getUsername());
        }

        if (entity.getFuentes() != null && !entity.getFuentes().isEmpty()) {
            dto.setNombresFuentesConfiables(
                    entity.getFuentes()
                            .stream()
                            .map(f -> f.getNombreEntidad())
                            .toList()
            );
        } else {
            dto.setNombresFuentesConfiables(List.of());
        }

        return dto;
    }

    /**
     * Convierte una lista de entidades {@link Advertencia} en una lista de {@link AdvertenciaDTO}.
     *
     * @param entities lista de entidades
     * @return lista de DTOs (vacía si la entrada es null)
     */
    public static List<AdvertenciaDTO> toDTOList(List<Advertencia> entities) {

        if (entities == null) return List.of();

        return entities
                .stream()
                .map(AdvertenciaMapper::toDTO)
                .toList();
    }

    /**
     * Convierte una entidad {@link Advertencia} en un {@link AdvertenciaDetailDTO}.
     *
     * <p>Incluye información completa de la advertencia, como descripción,
     * datos del usuario (nombre y email) y fuentes confiables.</p>
     *
     * @param entity entidad a convertir
     * @return DTO detallado o null si la entidad es null
     */
    public static AdvertenciaDetailDTO toDetailDTO(Advertencia entity) {

        if (entity == null) return null;

        AdvertenciaDetailDTO dto = new AdvertenciaDetailDTO();

        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setNivelCriticidad(entity.getNivelCriticidad());
        dto.setDescripcion(entity.getDescripcion());
        dto.setEsEmergencia(entity.getEsEmergencia());
        dto.setFechaEnvio(entity.getFechaEnvio());

        if (entity.getUser() != null) {
            dto.setNombreUsuario(entity.getUser().getUsername());
            dto.setEmailUsuario(entity.getUser().getGmail());
        }

        if (entity.getFuentes() != null && !entity.getFuentes().isEmpty()) {
            dto.setNombresFuentesConfiables(
                    entity.getFuentes()
                            .stream()
                            .map(f -> f.getNombreEntidad())
                            .toList()
            );
        } else {
            dto.setNombresFuentesConfiables(List.of());
        }

        return dto;
    }

    /**
     * Convierte un {@link AdvertenciaCreateDTO} en una entidad {@link Advertencia}.
     *
     * <p>Asocia la advertencia al usuario proporcionado.</p>
     *
     * @param dto datos de creación
     * @param usuario usuario asociado a la advertencia
     * @return entidad creada o null si el DTO es null
     */
    public static Advertencia toEntity(AdvertenciaCreateDTO dto, User usuario) {

        if (dto == null) return null;

        Advertencia entity = new Advertencia();

        entity.setTitulo(dto.getTitulo());
        entity.setNivelCriticidad(dto.getNivelCriticidad());
        entity.setDescripcion(dto.getDescripcion());
        entity.setEsEmergencia(dto.getEsEmergencia());
        entity.setUser(usuario);

        return entity;
    }

    /**
     * Copia los datos de un {@link AdvertenciaUpdateDTO} sobre una entidad existente.
     *
     * <p>Se utiliza en operaciones de actualización para modificar únicamente
     * los campos permitidos sin crear una nueva entidad.</p>
     *
     * @param dto datos de actualización
     * @param entity entidad a modificar
     */
    public static void copyToExistingEntity(AdvertenciaUpdateDTO dto, Advertencia entity) {

        if (dto == null || entity == null) return;

        entity.setTitulo(dto.getTitulo());
        entity.setNivelCriticidad(dto.getNivelCriticidad());
        entity.setDescripcion(dto.getDescripcion());
        entity.setEsEmergencia(dto.getEsEmergencia());
    }

    /**
     * Convierte una entidad {@link Advertencia} en un {@link AdvertenciaUpdateDTO}.
     *
     * <p>Se utiliza para precargar formularios de edición con los datos actuales.</p>
     *
     * @param entity entidad origen
     * @return DTO de actualización o null si la entidad es null
     */
    public static AdvertenciaUpdateDTO toUpdateDTO(Advertencia entity) {

        if (entity == null) return null;

        AdvertenciaUpdateDTO dto = new AdvertenciaUpdateDTO();

        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setNivelCriticidad(entity.getNivelCriticidad());
        dto.setDescripcion(entity.getDescripcion());
        dto.setEsEmergencia(entity.getEsEmergencia());

        return dto;
    }
}