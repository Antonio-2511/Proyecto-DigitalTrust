package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDetailDTO {

    private Long id;

    private String titulo;

    private String descripcion;

    private LocalDateTime fechaReporte;

    private String nombreUsuario;

    private String emailUsuario;
}
