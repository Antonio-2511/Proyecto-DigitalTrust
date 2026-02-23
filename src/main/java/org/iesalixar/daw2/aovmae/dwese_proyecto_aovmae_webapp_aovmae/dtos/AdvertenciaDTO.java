package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertenciaDTO {

    private Long id;

    private String titulo;

    private String nivelCriticidad;

    private boolean esEmergencia;

    private LocalDateTime fechaEnvio;

    private String nombreUsuario;

    private String nombreFuenteConfiable;

    private List<String> nombresFuentesConfiables;
}
