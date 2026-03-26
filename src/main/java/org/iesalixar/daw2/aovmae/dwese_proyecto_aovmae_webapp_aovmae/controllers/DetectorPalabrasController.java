package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.DetectorPalabrasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Controlador para la detección de estafas integrado con Thymeleaf.
 */
@Controller
@RequestMapping("/detector-mensajes-sospechosos")
public class DetectorPalabrasController {

    private final DetectorPalabrasService detectorService;

    public DetectorPalabrasController(DetectorPalabrasService detectorService) {
        this.detectorService = detectorService;
    }

    /**
     * Muestra la página del detector (GET)
     */
    @GetMapping
    public String mostrarPagina(Model model) {
        model.addAttribute("resultado", null);
        model.addAttribute("textoOriginal", "");
        // CAMBIA "detector" POR EL NOMBRE EXACTO DEL ARCHIVO
        return "detector-mensajes-sospechosos";
    }

    @PostMapping
    public String analizar(@RequestParam("contenidoTexto") String contenidoTexto,
                           Principal principal,
                           Model model) {

        if (contenidoTexto != null && !contenidoTexto.isBlank()) {
            // Obtenemos el username del usuario logueado
            if (principal == null) {
                // Si no está logueado, lo mandamos al login para evitar el error de null
                return "redirect:/login";
            }

            String username = principal.getName();

            // Llamamos al servicio con el usuario detectado
            AdvertenciaDTO resultado = detectorService.analizarMensaje(contenidoTexto, username);

            model.addAttribute("resultado", resultado);
            model.addAttribute("textoOriginal", contenidoTexto);
        }
        return "detector-mensajes-sospechosos";
    }

    /**
     * Si necesitas seguir exponiendo la lista de advertencias como JSON para una tabla AJAX,
     * este método usa @ResponseBody para saltarse la resolución de vistas HTML.
     */
    @GetMapping("/api/todos")
    @ResponseBody
    public List<AdvertenciaDTO> getAll() {
        return detectorService.listAll();
    }
}