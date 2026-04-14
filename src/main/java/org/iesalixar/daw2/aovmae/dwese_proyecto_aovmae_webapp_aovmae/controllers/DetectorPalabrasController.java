package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.DetectorPalabrasService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
     * 🔐 SOLO USUARIOS AUTENTICADOS
     */
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String mostrarPagina(Model model) {
        model.addAttribute("resultado", null);
        model.addAttribute("textoOriginal", "");
        return "detector-mensajes-sospechosos";
    }

    /**
     * 🔐 SOLO USUARIOS AUTENTICADOS
     */
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public String analizar(@RequestParam("contenidoTexto") String contenidoTexto,
                           Model model) {

        if (contenidoTexto != null && !contenidoTexto.isBlank()) {

            // 🔥 OBTENER USUARIO DESDE SECURITY (MEJOR QUE PRINCIPAL)
            String username = SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName();

            AdvertenciaDTO resultado =
                    detectorService.analizarMensaje(contenidoTexto, username);

            model.addAttribute("resultado", resultado);
            model.addAttribute("textoOriginal", contenidoTexto);
        }

        return "detector-mensajes-sospechosos";
    }

    /**
     * 🔐 SOLO STAFF
     */
    @GetMapping("/api/todos")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public List<AdvertenciaDTO> getAll() {
        return detectorService.listAll();
    }
}