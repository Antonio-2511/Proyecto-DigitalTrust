package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {

    private Long id;

    private String username;

    private String email;

    private String telefono;

    private LocalDateTime fechaCreacion;

    private String planNombre;
}
