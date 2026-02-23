package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeCreateDTO {

    @NotBlank
    @Size(max = 255)
    private String contenidoTexto;

    @NotBlank
    @Size(max = 45)
    private String origen;

    @Size(max = 45)
    private String nivelRiesgo;

    @Size(max = 45)
    private String resultadoAnalisis;

    @NotNull
    private Long fuenteConfiableId;

    @NotBlank
    private String usernameUsuario;
}