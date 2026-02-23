package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDTO {

    private Long idMensaje;

    private String contenidoTexto;

    private String origen;

    private String nivelRiesgo;

    private String resultadoAnalisis;

    private String nombreUsuario;

    private String nombreFuenteConfiable;
}