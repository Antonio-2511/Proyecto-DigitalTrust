package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.*;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.FuenteConfiable;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Mensaje;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;

import java.util.List;

public class MensajeMapper {

    public static MensajeDTO toDTO(Mensaje entity) {
        if (entity == null) return null;

        MensajeDTO dto = new MensajeDTO();
        dto.setIdMensaje(entity.getIdMensaje());
        dto.setContenidoTexto(entity.getContenidoTexto());
        dto.setOrigen(entity.getOrigen());
        dto.setNivelRiesgo(entity.getNivelRiesgo());
        dto.setResultadoAnalisis(entity.getResultadoAnalisis());

        if (entity.getUser() != null) {
            dto.setNombreUsuario(entity.getUser().getUsername());
        }
        if (entity.getFuenteConfiable() != null) {
            dto.setNombreFuenteConfiable(entity.getFuenteConfiable().getNombreEntidad());
        }

        return dto;
    }

    public static List<MensajeDTO> toDTOList(List<Mensaje> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .map(MensajeMapper::toDTO)
                .toList();
    }

    public static MensajeDetailDTO toDetailDTO(Mensaje entity) {
        if (entity == null) return null;

        MensajeDetailDTO dto = new MensajeDetailDTO();
        dto.setIdMensaje(entity.getIdMensaje());
        dto.setContenidoTexto(entity.getContenidoTexto());
        dto.setOrigen(entity.getOrigen());
        dto.setNivelRiesgo(entity.getNivelRiesgo());
        dto.setResultadoAnalisis(entity.getResultadoAnalisis());
        dto.setFechaAnalisis(entity.getFechaAnalisis());

        if (entity.getUser() != null) {
            dto.setNombreUsuario(entity.getUser().getUsername());
            dto.setEmailUsuario(entity.getUser().getGmail());
        }
        if (entity.getFuenteConfiable() != null) {
            dto.setNombreFuenteConfiable(entity.getFuenteConfiable().getNombreEntidad());
        }

        return dto;
    }

    public static Mensaje toEntity(
            MensajeCreateDTO dto,
            User usuario,
            FuenteConfiable fuenteConfiable) {

        if (dto == null) return null;

        Mensaje entity = new Mensaje();
        entity.setContenidoTexto(dto.getContenidoTexto());
        entity.setOrigen(dto.getOrigen());
        entity.setNivelRiesgo(dto.getNivelRiesgo());
        entity.setResultadoAnalisis(dto.getResultadoAnalisis());
        entity.setUser(usuario);
        entity.setFuenteConfiable(fuenteConfiable);

        return entity;
    }

    public static void copyToExistingEntity(
            MensajeUpdateDTO dto,
            Mensaje entity,
            FuenteConfiable fuenteConfiable) {

        if (dto == null || entity == null) return;

        entity.setContenidoTexto(dto.getContenidoTexto());
        entity.setOrigen(dto.getOrigen());
        entity.setNivelRiesgo(dto.getNivelRiesgo());
        entity.setResultadoAnalisis(dto.getResultadoAnalisis());
        entity.setFuenteConfiable(fuenteConfiable);
    }
}