package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDetailDTO {

    private Long idMensaje;

    private String contenidoTexto;

    private String origen;

    private String nivelRiesgo;

    private String resultadoAnalisis;

    private LocalDateTime fechaAnalisis;

    private String nombreUsuario;

    private String emailUsuario;

    private String nombreFuenteConfiable;
}