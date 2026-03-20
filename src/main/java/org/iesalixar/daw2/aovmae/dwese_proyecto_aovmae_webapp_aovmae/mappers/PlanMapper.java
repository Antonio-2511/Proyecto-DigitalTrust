package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.*;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Plan;

import java.util.List;

/**
 * Clase utilitaria encargada de la conversión (mapping) entre la entidad
 * {@link Plan} y sus diferentes DTOs asociados.
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
public class PlanMapper {

    /**
     * Convierte una entidad {@link Plan} en un {@link PlanDTO}.
     *
     * <p>Incluye únicamente la información básica del plan, utilizada
     * generalmente en listados o respuestas simplificadas.</p>
     *
     * @param entity entidad a convertir
     * @return DTO resultante o null si la entidad es null
     */
    public static PlanDTO toDTO(Plan entity) {
        if (entity == null) return null;

        PlanDTO dto = new PlanDTO();
        dto.setNombrePlan(entity.getNombrePlan());
        dto.setBeneficios(entity.getBeneficios());
        dto.setPrecio(entity.getPrecio());

        return dto;
    }

    /**
     * Convierte una lista de entidades {@link Plan} en una lista de {@link PlanDTO}.
     *
     * @param entities lista de entidades
     * @return lista de DTOs (vacía si la entrada es null)
     */
    public static List<PlanDTO> toDTOList(List<Plan> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .map(PlanMapper::toDTO)
                .toList();
    }

    /**
     * Convierte una entidad {@link Plan} en un {@link PlanDetailDTO}.
     *
     * <p>Incluye información completa del plan, como fechas de validez
     * y los usuarios asociados al mismo.</p>
     *
     * @param entity entidad a convertir
     * @return DTO detallado o null si la entidad es null
     */
    public static PlanDetailDTO toDetailDTO(Plan entity) {
        if (entity == null) return null;

        PlanDetailDTO dto = new PlanDetailDTO();
        dto.setNombrePlan(entity.getNombrePlan());
        dto.setBeneficios(entity.getBeneficios());
        dto.setPrecio(entity.getPrecio());
        dto.setFechaInicio(entity.getFechaInicio());
        dto.setFechaExpiracion(entity.getFechaExpiracion());

        if (entity.getUsers() != null && !entity.getUsers().isEmpty()) {
            dto.setUsernames(entity.getUsers()
                    .stream()
                    .map(u -> u.getUsername())
                    .toList());
        }

        return dto;
    }

    /**
     * Convierte un {@link PlanCreateDTO} en una entidad {@link Plan}.
     *
     * @param dto datos de creación del plan
     * @return entidad creada o null si el DTO es null
     */
    public static Plan toEntity(PlanCreateDTO dto) {
        if (dto == null) return null;

        Plan entity = new Plan();
        entity.setNombrePlan(dto.getNombrePlan());
        entity.setBeneficios(dto.getBeneficios());
        entity.setPrecio(dto.getPrecio());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaExpiracion(dto.getFechaExpiracion());

        return entity;
    }

    /**
     * Copia los datos de un {@link PlanUpdateDTO} sobre una entidad existente.
     *
     * <p>Se utiliza en operaciones de actualización para modificar los campos
     * permitidos sin alterar la clave primaria.</p>
     *
     * <p><strong>Nota:</strong> El campo {@code nombrePlan} no se actualiza ya que
     * actúa como clave primaria del sistema.</p>
     *
     * @param dto datos de actualización
     * @param entity entidad a modificar
     */
    public static void copyToExistingEntity(PlanUpdateDTO dto, Plan entity) {
        if (dto == null || entity == null) return;

        entity.setBeneficios(dto.getBeneficios());
        entity.setPrecio(dto.getPrecio());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaExpiracion(dto.getFechaExpiracion());
        // nombrePlan es PK, normalmente no se actualiza
    }
}