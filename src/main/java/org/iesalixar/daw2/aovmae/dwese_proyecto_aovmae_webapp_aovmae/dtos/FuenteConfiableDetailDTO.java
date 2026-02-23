package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuenteConfiableDetailDTO {

    private Long idFuente;

    private String nombreEntidad;

    private String tipo;

    private String telefono;

    private String email;

    private String dominio;

    private Long advertenciaId;

    private String tituloAdvertencia;

    private List<String> mensajes; // opcional: lista de contenidos de mensajes asociados
}