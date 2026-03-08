package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuenteConfiableDTO {

    private Long idFuente;

    private String nombreEntidad;

    private String tipo;

    private String telefono;

    private String email;

    private String dominio;

    // Relación con advertencias
    private List<Long> advertenciasIds;
}