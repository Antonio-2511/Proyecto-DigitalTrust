package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaUpdateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Advertencia;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;

import java.util.List;

public class AdvertenciaMapper {

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
                    entity.getFuentes().stream()
                            .map(f -> f.getNombreEntidad())
                            .toList()
            );
        }

        return dto;
    }

    public static List<AdvertenciaDTO> toDTOList(List<Advertencia> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .map(AdvertenciaMapper::toDTO)
                .toList();
    }

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
                    entity.getFuentes().stream()
                            .map(f -> f.getNombreEntidad())
                            .toList()
            );
        }

        return dto;
    }

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

    public static void copyToExistingEntity(AdvertenciaUpdateDTO dto, Advertencia entity) {
        if (dto == null || entity == null) return;

        entity.setTitulo(dto.getTitulo());
        entity.setNivelCriticidad(dto.getNivelCriticidad());
        entity.setDescripcion(dto.getDescripcion());
        entity.setEsEmergencia(dto.getEsEmergencia());
    }
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