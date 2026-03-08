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
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Override
    public Page<UserDTO> list(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .map(UserMapper::toDTO);
    }

    @Override
    public UserUpdateDTO getForEdit(String username) {

        User usuario = userRepository.findById(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("usuario", "username", username)
                );

        return UserMapper.toUpdateDTO(usuario);
    }

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
                        new ResourceNotFoundException("plan", "id", dto.getPlanId())
                );

        User usuario = UserMapper.toEntity(dto, plan);
        userRepository.save(usuario);
    }

    @Override
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
                        new ResourceNotFoundException("plan", "id", dto.getPlanId())
                );

        UserMapper.copyToExistingEntity(dto, usuario, plan);
        userRepository.save(usuario);
    }

    @Override
    public void delete(String username) {

        if (!userRepository.existsById(username)) {
            throw new ResourceNotFoundException("usuario", "username", username);
        }

        userRepository.deleteById(username);
    }

    @Override
    public UserDetailDTO getDetail(String username) {

        User usuario = userRepository.findById(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("usuario", "username", username)
                );

        return UserMapper.toDetailDTO(usuario);
    }

    @Override
    public User getAuthenticatedUser() {
        return null;
    }

}