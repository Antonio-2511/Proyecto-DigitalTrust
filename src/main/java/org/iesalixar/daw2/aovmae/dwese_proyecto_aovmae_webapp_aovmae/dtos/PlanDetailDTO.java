package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDetailDTO {

    private String nombrePlan;
    private String beneficios;
    private Double precio;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaExpiracion;

    private List<String> usernames; // lista de usuarios que tienen este plan
}