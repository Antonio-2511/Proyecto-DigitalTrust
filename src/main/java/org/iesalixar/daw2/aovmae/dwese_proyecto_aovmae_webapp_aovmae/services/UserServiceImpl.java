package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import jakarta.transaction.Transactional;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserUpdateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Plan;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.exceptions.DuplicateResourceException;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.exceptions.ResourceNotFoundException;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers.UserMapper;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.PlanRepository;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio {@link UserService}.
 * <p>
 * Gestiona la lógica de negocio relacionada con los usuarios,
 * incluyendo validaciones, asociación con planes y seguridad en contraseñas.
 * </p>
 *
 * <p>
 * 🔐 Este servicio es crítico ya que maneja credenciales y datos sensibles.
 * Incluye cifrado de contraseñas mediante {@link PasswordEncoder}.
 * </p>
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    /**
     * Repositorio de acceso a datos para usuarios.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Repositorio para la gestión de planes asociados a usuarios.
     */
    @Autowired
    private PlanRepository planRepository;

    /**
     * Codificador de contraseñas para almacenamiento seguro.
     */
    @Autowired
    private PasswordEncoder passwordEncoder; // 🔥 CLAVE

    /**
     * Obtiene una lista paginada de usuarios.
     *
     * @param pageable configuración de paginación
     * @return página de {@link UserDTO}
     */
    @Override
    public Page<UserDTO> list(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .map(UserMapper::toDTO);
    }

    /**
     * Obtiene un usuario para su edición.
     *
     * @param username identificador del usuario
     * @return {@link UserUpdateDTO} con los datos editables
     * @throws ResourceNotFoundException si el usuario no existe
     */
    @Override
    public UserUpdateDTO getForEdit(String username) {

        User usuario = userRepository.findById(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("usuario", "username", username)
                );

        return UserMapper.toUpdateDTO(usuario);
    }

    /**
     * Crea un nuevo usuario en el sistema.
     * <p>
     * Realiza validaciones de unicidad y asigna un plan al usuario.
     * Además, cifra la contraseña antes de almacenarla.
     * </p>
     *
     * @param dto datos de creación del usuario
     * @throws DuplicateResourceException si el email o username ya existen
     * @throws ResourceNotFoundException si el plan no existe
     */
    @Override
    public void create(UserCreateDTO dto) {

        if (userRepository.existsByGmail(dto.getEmail())) {
            throw new DuplicateResourceException("usuario", "email", dto.getEmail());
        }

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new DuplicateResourceException("usuario", "nombreUsuario", dto.getUsername());
        }

        /**
         * Obtención del plan asociado al usuario.
         */
        Plan plan = planRepository.findById(dto.getPlanId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("plan", "nombrePlan", dto.getPlanId())
                );

        /**
         * Conversión del DTO a entidad.
         */
        User usuario = UserMapper.toEntity(dto, plan);

        /**
         * Cifrado de la contraseña antes de persistir.
         */
        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));

        userRepository.save(usuario);
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param dto datos actualizados del usuario
     * @throws DuplicateResourceException si el email ya está en uso por otro usuario
     * @throws ResourceNotFoundException si el usuario o plan no existen
     */
    @Override
    public void update(UserUpdateDTO dto) {

        if (userRepository.existsByGmailAndUsernameNot(dto.getEmail(), dto.getUsername())) {
            throw new DuplicateResourceException("usuario", "email", dto.getEmail());
        }

        User usuario = userRepository.findById(dto.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFoundException("usuario", "username", dto.getUsername())
                );

        /**
         * Obtención del plan actualizado.
         */
        Plan plan = planRepository.findById(dto.getPlanId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("plan", "nombrePlan", dto.getPlanId())
                );

        /**
         * Copia de datos actualizados a la entidad existente.
         */
        UserMapper.copyToExistingEntity(dto, usuario, plan);

        /**
         * Actualización de contraseña solo si se proporciona una nueva.
         */
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            usuario.setContrasenia(passwordEncoder.encode(dto.getPassword()));
        }

        userRepository.save(usuario);
    }

    /**
     * Elimina un usuario por su username.
     *
     * @param username identificador del usuario
     * @throws ResourceNotFoundException si el usuario no existe
     */
    @Override
    public void delete(String username) {

        if (!userRepository.existsById(username)) {
            throw new ResourceNotFoundException("usuario", "username", username);
        }

        userRepository.deleteById(username);
    }

    /**
     * Obtiene el detalle completo de un usuario.
     *
     * @param username identificador del usuario
     * @return {@link UserDetailDTO} con la información detallada
     * @throws ResourceNotFoundException si el usuario no existe
     */
    @Override
    public UserDetailDTO getDetail(String username) {

        User usuario = userRepository.findById(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("usuario", "username", username)
                );

        return UserMapper.toDetailDTO(usuario);
    }

    /**
     * Obtiene el usuario autenticado en el sistema.
     * <p>
     * ⚠️ Actualmente no está implementado (retorna null).
     * Debe integrarse con el contexto de seguridad de Spring Security.
     * </p>
     *
     * @return usuario autenticado o null si no está implementado
     */
    @Override
    public User getAuthenticatedUser() {
        return null;
    }
}