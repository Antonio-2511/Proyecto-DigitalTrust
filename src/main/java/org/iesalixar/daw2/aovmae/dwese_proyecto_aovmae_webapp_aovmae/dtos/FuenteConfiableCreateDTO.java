package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuenteConfiableCreateDTO {

    @NotBlank
    @Size(max = 150)
    private String nombreEntidad;

    @NotBlank
    @Size(max = 50)
    private String tipo;

    @Size(max = 15)
    private String telefono;

    @Email
    @Size(max = 150)
    private String email;

    @Size(max = 150)
    private String dominio;

    // Asociación opcional con advertencias
    private List<Long> advertenciasIds;
}