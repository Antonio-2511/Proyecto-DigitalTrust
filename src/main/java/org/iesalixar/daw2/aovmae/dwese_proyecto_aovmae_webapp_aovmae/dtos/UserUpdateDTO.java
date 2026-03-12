package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    private String email;

    @Size(max = 15)
    private String telefono;

    @NotNull
    private String planId;

    private Boolean cambiarPassword = false;

    @Size(min = 8, max = 100)
    private String password;

    private String confirmPassword;
}