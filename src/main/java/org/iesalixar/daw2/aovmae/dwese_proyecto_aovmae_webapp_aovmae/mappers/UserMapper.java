package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserUpdateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Plan;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;

import java.util.List;

/**
 * Clase utilitaria encargada de la conversión (mapping) entre la entidad
 * {@link User} y sus diferentes DTOs asociados.
 *
 * <p>Centraliza la lógica de transformación entre la capa de persistencia
 * y la capa de presentación, evitando el acoplamiento directo entre entidades
 * y DTOs.</p>
 *
 * <p>Incluye métodos para:
 * <ul>
 *     <li>Conversión de entidad a DTO básico (listados)</li>
 *     <li>Conversión de entidad a DTO detallado</li>
 *     <li>Conversión a DTO de actualización</li>
 *     <li>Conversión de DTO de creación a entidad</li>
 *     <li>Actualización de entidades existentes</li>
 * </ul>
 * </p>
 *
 * <p>Todos los métodos son estáticos, ya que la clase no mantiene estado.</p>
 *
 * <p><strong>Nota de seguridad:</strong> No se incluyen datos sensibles como contraseñas
 * en ningún proceso de conversión a DTO.</p>
 *
 * @author
 */
public class UserMapper {

    /* ==============================
       ENTITY -> DTO (LIST)
       ============================== */

    /**
     * Convierte una entidad {@link User} en un {@link UserDTO}.
     *
     * <p>Incluye información básica del usuario para su uso en listados.</p>
     *
     * @param entity entidad a convertir
     * @return DTO resultante o null si la entidad es null
     */
    public static UserDTO toDTO(User entity) {

        if (entity == null) return null;

        UserDTO dto = new UserDTO();

        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getGmail());
        dto.setTelefono(entity.getTelefono());

        return dto;
    }

    /**
     * Convierte una lista de entidades {@link User} en una lista de {@link UserDTO}.
     *
     * @param entities lista de entidades
     * @return lista de DTOs (vacía si la entrada es null)
     */
    public static List<UserDTO> toDTOList(List<User> entities) {

        if (entities == null) return List.of();

        return entities
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }


    /* ==============================
       ENTITY -> DETAIL DTO
       ============================== */

    /**
     * Convierte una entidad {@link User} en un {@link UserDetailDTO}.
     *
     * <p>Incluye información completa del usuario, como fecha de creación
     * y el nombre del plan asociado.</p>
     *
     * @param entity entidad a convertir
     * @return DTO detallado o null si la entidad es null
     */
    public static UserDetailDTO toDetailDTO(User entity) {

        if (entity == null) return null;

        UserDetailDTO dto = new UserDetailDTO();

        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getGmail());
        dto.setTelefono(entity.getTelefono());
        dto.setFechaCreacion(entity.getFechaCreacion());

        /* AQUÍ ESTABA EL PROBLEMA */
        if (entity.getPlan() != null) {
            dto.setPlanNombre(entity.getPlan().getNombrePlan());
        }

        return dto;
    }


    /* ==============================
       ENTITY -> UPDATE DTO
       ============================== */

    /**
     * Convierte una entidad {@link User} en un {@link UserUpdateDTO}.
     *
     * <p>Se utiliza para precargar formularios de edición con los datos actuales del usuario.</p>
     *
     * @param entity entidad origen
     * @return DTO de actualización o null si la entidad es null
     */
    public static UserUpdateDTO toUpdateDTO(User entity) {

        if (entity == null) return null;

        UserUpdateDTO dto = new UserUpdateDTO();

        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getGmail());
        dto.setTelefono(entity.getTelefono());

        if (entity.getPlan() != null) {
            dto.setPlanId(entity.getPlan().getNombrePlan());
        }

        return dto;
    }


    /* ==============================
       CREATE DTO -> ENTITY
       ============================== */

    /**
     * Convierte un {@link UserCreateDTO} en una entidad {@link User}.
     *
     * <p>Asocia el usuario con el plan proporcionado.</p>
     *
     * @param dto datos de creación
     * @param plan plan asociado al usuario
     * @return entidad creada o null si el DTO es null
     */
    public static User toEntity(UserCreateDTO dto, Plan plan) {

        if (dto == null) return null;

        User entity = new User();

        entity.setUsername(dto.getUsername());
        entity.setGmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());
        entity.setPlan(plan);

        return entity;
    }


    /* ==============================
       UPDATE DTO -> ENTITY
       ============================== */

    /**
     * Copia los datos de un {@link UserUpdateDTO} sobre una entidad existente.
     *
     * <p>Se utiliza en operaciones de actualización para modificar únicamente
     * los campos permitidos, incluyendo la posible actualización del plan asociado.</p>
     *
     * @param dto datos de actualización
     * @param entity entidad a modificar
     * @param plan nuevo plan asociado
     */
    public static void copyToExistingEntity(UserUpdateDTO dto, User entity, Plan plan) {

        if (dto == null || entity == null) return;

        entity.setUsername(dto.getUsername());
        entity.setGmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());
        entity.setPlan(plan);
    }
}