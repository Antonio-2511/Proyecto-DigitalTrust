package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers;


import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserUpdateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Plan;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;

import java.util.List;

public class UserMapper {



    public static UserDTO toDTO(User entity) {
        if (entity == null) return null;

        UserDTO dto = new UserDTO();
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getGmail());
        dto.setTelefono(entity.getTelefono());


        return dto;
    }

    public static List<UserDTO> toDTOList(List<User> entities) {
        if (entities == null) return List.of();
        return entities.stream().map(UserMapper::toDTO).toList();
    }


    public static UserDetailDTO toDetailDTO(User entity) {
        if (entity == null) return null;

        UserDetailDTO dto = new UserDetailDTO();
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getGmail());
        dto.setTelefono(entity.getTelefono());
        dto.setFechaCreacion(entity.getFechaCreacion());


        return dto;
    }


    public static UserUpdateDTO toUpdateDTO(User entity) {
        if (entity == null) return null;

        UserUpdateDTO dto = new UserUpdateDTO();
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getGmail());
        dto.setTelefono(entity.getTelefono());
        

        return dto;
    }


    public static User toEntity(UserCreateDTO dto, Plan plan) {
        if (dto == null) return null;

        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setGmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());
        entity.setPlan(plan);

        return entity;
    }

    public static void copyToExistingEntity(UserUpdateDTO dto, User entity, Plan plan) {
        if (dto == null || entity == null) return;

        entity.setUsername(dto.getUsername());
        entity.setGmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());
        entity.setPlan(plan);
    }
}
