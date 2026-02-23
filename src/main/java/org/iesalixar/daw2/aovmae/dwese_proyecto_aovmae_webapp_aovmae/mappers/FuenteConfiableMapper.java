package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.*;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Advertencia;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.FuenteConfiable;

import java.util.List;

public class FuenteConfiableMapper {

    public static FuenteConfiableDTO toDTO(FuenteConfiable entity) {
        if (entity == null) return null;

        FuenteConfiableDTO dto = new FuenteConfiableDTO();
        dto.setIdFuente(entity.getIdFuente());
        dto.setNombreEntidad(entity.getNombreEntidad());
        dto.setTipo(entity.getTipo());
        dto.setTelefono(entity.getTelefono());
        dto.setEmail(entity.getEmail());
        dto.setDominio(entity.getDominio());

        if (entity.getAdvertencia() != null) {
            dto.setAdvertenciaId(entity.getAdvertencia().getId());
        }

        return dto;
    }

    public static List<FuenteConfiableDTO> toDTOList(List<FuenteConfiable> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .map(FuenteConfiableMapper::toDTO)
                .toList();
    }

    public static FuenteConfiableDetailDTO toDetailDTO(FuenteConfiable entity) {
        if (entity == null) return null;

        FuenteConfiableDetailDTO dto = new FuenteConfiableDetailDTO();
        dto.setIdFuente(entity.getIdFuente());
        dto.setNombreEntidad(entity.getNombreEntidad());
        dto.setTipo(entity.getTipo());
        dto.setTelefono(entity.getTelefono());
        dto.setEmail(entity.getEmail());
        dto.setDominio(entity.getDominio());

        if (entity.getAdvertencia() != null) {
            dto.setAdvertenciaId(entity.getAdvertencia().getId());
            dto.setTituloAdvertencia(entity.getAdvertencia().getTitulo());
        }

        return dto;
    }

    public static FuenteConfiable toEntity(
            FuenteConfiableCreateDTO dto,
            Advertencia advertencia) {

        if (dto == null) return null;

        FuenteConfiable entity = new FuenteConfiable();
        entity.setNombreEntidad(dto.getNombreEntidad());
        entity.setTipo(dto.getTipo());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());
        entity.setDominio(dto.getDominio());
        entity.setAdvertencia(advertencia);

        return entity;
    }

    public static void copyToExistingEntity(
            FuenteConfiableUpdateDTO dto,
            FuenteConfiable entity,
            Advertencia advertencia) {

        if (dto == null || entity == null) return;

        entity.setNombreEntidad(dto.getNombreEntidad());
        entity.setTipo(dto.getTipo());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());
        entity.setDominio(dto.getDominio());
        entity.setAdvertencia(advertencia);
    }
}