package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.*;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Plan;

import java.util.List;

public class PlanMapper {

    public static PlanDTO toDTO(Plan entity) {
        if (entity == null) return null;

        PlanDTO dto = new PlanDTO();
        dto.setNombrePlan(entity.getNombrePlan());
        dto.setBeneficios(entity.getBeneficios());
        dto.setPrecio(entity.getPrecio());

        return dto;
    }

    public static List<PlanDTO> toDTOList(List<Plan> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .map(PlanMapper::toDTO)
                .toList();
    }

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

    public static void copyToExistingEntity(PlanUpdateDTO dto, Plan entity) {
        if (dto == null || entity == null) return;

        entity.setBeneficios(dto.getBeneficios());
        entity.setPrecio(dto.getPrecio());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaExpiracion(dto.getFechaExpiracion());
        // nombrePlan es PK, normalmente no se actualiza
    }
}