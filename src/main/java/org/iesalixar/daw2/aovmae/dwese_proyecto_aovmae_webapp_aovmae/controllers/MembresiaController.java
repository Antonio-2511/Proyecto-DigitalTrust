package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/planes") // Coincide con th:href="@{/planes}" de tu header
public class MembresiaController {

    @GetMapping
    public String mostrarMembresias() {
        // Busca el archivo membresia.html en src/main/resources/templates/
        return "membresia";
    }
}