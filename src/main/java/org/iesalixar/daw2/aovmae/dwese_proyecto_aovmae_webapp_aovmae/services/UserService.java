package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;


import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserUpdateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interfaz de servicio para la gestión de usuarios del sistema.
 * <p>
 * Define las operaciones de negocio relacionadas con la administración
 * de usuarios, incluyendo creación, actualización, eliminación y consulta.
 * </p>
 *
 * <p>
 * Además, proporciona acceso al usuario autenticado, lo cual es esencial
 * para funcionalidades de seguridad y control de acceso.
 * </p>
 */
public interface UserService {

    /**
     * Obtiene una lista paginada de usuarios.
     *
     * @param pageable configuración de paginación
     * @return página ({@link Page}) de {@link UserDTO}
     */
    Page<UserDTO> list(Pageable pageable);

    /**
     * Obtiene un usuario para su edición.
     *
     * @param username nombre de usuario
     * @return {@link UserUpdateDTO} con los datos editables
     */
    UserUpdateDTO getForEdit(String username);

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param dto datos necesarios para la creación del usuario
     */
    void create(UserCreateDTO dto);

    /**
     * Actualiza un usuario existente.
     *
     * @param dto datos actualizados del usuario
     */
    void update(UserUpdateDTO dto);

    /**
     * Elimina un usuario del sistema.
     *
     * @param username nombre de usuario a eliminar
     */
    void delete(String username);

    /**
     * Obtiene el detalle completo de un usuario.
     *
     * @param username nombre de usuario
     * @return {@link UserDetailDTO} con la información detallada
     */
    UserDetailDTO getDetail(String username);

    /**
     * Obtiene el usuario actualmente autenticado en el sistema.
     * <p>
     * Este método es clave para operaciones que dependen del contexto de seguridad,
     * como la creación de reportes o auditoría de acciones.
     * </p>
     *
     * @return entidad {@link User} del usuario autenticado
     */
    User getAuthenticatedUser();

}