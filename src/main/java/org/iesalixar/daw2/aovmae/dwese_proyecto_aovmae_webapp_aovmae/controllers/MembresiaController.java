package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador encargado de la gestión de las membresías o planes.
 *
 * Gestiona la navegación hacia la página donde se muestran
 * los distintos planes disponibles para el usuario.
 *
 * Forma parte de la capa de presentación (MVC).
 */
@Controller
@RequestMapping("/planes") // Coincide con th:href="@{/planes}" de tu header
public class MembresiaController {

    /**
     * Muestra la página de membresías.
     *
     * Carga la vista "membresia.html" ubicada en:
     * src/main/resources/templates/
     *
     * @return nombre de la vista de membresías
     */
    @GetMapping
    public String mostrarMembresias() {
        // Busca el archivo membresia.html en src/main/resources/templates/
        return "membresia";
    }
}