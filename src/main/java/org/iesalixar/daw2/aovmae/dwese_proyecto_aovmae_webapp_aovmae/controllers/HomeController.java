package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador principal de la aplicación (Home).
 *
 * Gestiona:
 * - La página de inicio (landing page)
 * - Navegación hacia funcionalidades públicas como el detector de mensajes
 *
 * Forma parte de la capa de presentación en el patrón MVC.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /**
     *  PÚBLICO
     */
    @GetMapping
    public String index() {
        return "index";
    }

    /**
     *  Redirección inteligente al detector
     */
    @GetMapping("/detector")
    public String redirigirDetector() {
        return "redirect:/detector-mensajes-sospechosos";
    }
}