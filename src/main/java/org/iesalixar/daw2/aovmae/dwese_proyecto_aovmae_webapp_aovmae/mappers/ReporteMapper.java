package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers;


import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteUpdateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Reporte;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;

import java.util.List;

public class ReporteMapper {


    public static ReporteDTO toDTO(Reporte entity) {
        if (entity == null) return null;

        ReporteDTO dto = new ReporteDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setFechaReporte(entity.getFechaReporte());

        if (entity.getUser() != null) {
            dto.setNombreUsuario(entity.getUser().getUsername());
        }

        return dto;
    }

    public static List<ReporteDTO> toDTOList(List<Reporte> entities) {
        if (entities == null) return List.of();
        return entities.stream().map(ReporteMapper::toDTO).toList();
    }



    public static ReporteDetailDTO toDetailDTO(Reporte entity) {
        if (entity == null) return null;

        ReporteDetailDTO dto = new ReporteDetailDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescripcion(entity.getDescripcion());
        dto.setFechaReporte(entity.getFechaReporte());

        if (entity.getUser() != null) {
            dto.setNombreUsuario(entity.getUser().getUsername());
            dto.setEmailUsuario(entity.getUser().getGmail());
        }

        return dto;
    }


    public static Reporte toEntity(ReporteCreateDTO dto, User usuario) {
        if (dto == null) return null;

        Reporte entity = new Reporte();
        entity.setTitulo(dto.getTitulo());
        entity.setDescripcion(dto.getDescripcion());
        entity.setUser(usuario);

        return entity;
    }

    public static void copyToExistingEntity(ReporteUpdateDTO dto, Reporte entity) {
        if (dto == null || entity == null) return;

        entity.setTitulo(dto.getTitulo());
        entity.setDescripcion(dto.getDescripcion());
    }
}

