package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuenteConfiableCreateDTO {

    @NotBlank
    @Size(max = 100)
    private String nombreEntidad;

    @NotBlank
    @Size(max = 45)
    private String tipo;

    @Size(max = 15)
    private String telefono;

    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 45)
    private String dominio;

    @NotNull
    private Long advertenciaId;
}