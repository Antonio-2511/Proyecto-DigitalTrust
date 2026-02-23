package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    private String email;

    @Size(max = 15)
    private String telefono;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotNull
    private Long planId;
}
