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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 🔐 Solo ADMIN puede listar usuarios
     */
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserDTO> list(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .map(UserMapper::toDTO);
    }

    /**
     * 🔐 Usuario o ADMIN
     */
    @Override
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public UserUpdateDTO getForEdit(String username) {

        User usuario = userRepository.findById(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("usuario", "username", username)
                );

        return UserMapper.toUpdateDTO(usuario);
    }

    /**
     * 🔓 Público (registro)
     */
    @Override
    public void create(UserCreateDTO dto) {

        if (userRepository.existsByGmail(dto.getEmail())) {
            throw new DuplicateResourceException("usuario", "email", dto.getEmail());
        }

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new DuplicateResourceException("usuario", "nombreUsuario", dto.getUsername());
        }

        Plan plan = planRepository.findById(dto.getPlanId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("plan", "nombrePlan", dto.getPlanId())
                );

        User usuario = UserMapper.toEntity(dto, plan);

        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));

        userRepository.save(usuario);
    }

    /**
     * 🔐 Usuario o ADMIN
     */
    @Override
    @PreAuthorize("#dto.username == authentication.name or hasRole('ADMIN')")
    public void update(UserUpdateDTO dto) {

        if (userRepository.existsByGmailAndUsernameNot(dto.getEmail(), dto.getUsername())) {
            throw new DuplicateResourceException("usuario", "email", dto.getEmail());
        }

        User usuario = userRepository.findById(dto.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFoundException("usuario", "username", dto.getUsername())
                );

        Plan plan = planRepository.findById(dto.getPlanId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("plan", "nombrePlan", dto.getPlanId())
                );

        UserMapper.copyToExistingEntity(dto, usuario, plan);

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            usuario.setContrasenia(passwordEncoder.encode(dto.getPassword()));
        }

        userRepository.save(usuario);
    }

    /**
     * 🔐 SOLO ADMIN
     */
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String username) {

        if (!userRepository.existsById(username)) {
            throw new ResourceNotFoundException("usuario", "username", username);
        }

        userRepository.deleteById(username);
    }

    /**
     * 🔐 Usuario o ADMIN
     */
    @Override
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public UserDetailDTO getDetail(String username) {

        User usuario = userRepository.findById(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("usuario", "username", username)
                );

        return UserMapper.toDetailDTO(usuario);
    }

    /**
     * 🔐 Obtener usuario autenticado REAL
     */
    @Override
    public User getAuthenticatedUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        String username = auth.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    @Override
    public boolean isAdmin() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) return false;

        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }



}