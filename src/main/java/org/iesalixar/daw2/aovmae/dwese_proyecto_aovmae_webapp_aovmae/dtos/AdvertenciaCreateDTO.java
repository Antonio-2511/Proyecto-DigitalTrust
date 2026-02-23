package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertenciaCreateDTO {

    @NotBlank
    @Size(max = 100)
    private String titulo;

    @NotNull
    private String nivelCriticidad;

    @NotBlank
    private String descripcion;

    @NotNull
    private Boolean esEmergencia;

    @NotNull
    private Long fuenteConfiableId;

}
