package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.MensajeDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.MensajeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión y análisis de mensajes.
 *
 * Proporciona endpoints para:
 * - Obtener todos los mensajes analizados
 * - Analizar nuevos mensajes en busca de posibles estafas
 *
 * ⚠️ Seguridad:
 * Este tipo de API puede ser objetivo de:
 * - Envío masivo de peticiones (DoS)
 * - Payloads maliciosos o excesivamente grandes
 * - Intentos de evasión de detección
 *
 * Se recomienda:
 * - Validar y limitar el tamaño del contenido
 * - Implementar rate limiting
 * - Registrar actividad sospechosa
 */
@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    /**
     * 🔐 SOLO ADMIN o MODERATOR
     */
    @GetMapping("/todos")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public List<MensajeDTO> getAll() {
        return mensajeService.listAll();
    }

    /**
     * 🔐 SOLO USUARIOS AUTENTICADOS
     */
    @PostMapping("/analizar")
    @PreAuthorize("isAuthenticated()")
    public MensajeDTO analizar(@RequestBody String contenidoTexto) {

        // 🔒 validación básica anti abuso
        if (contenidoTexto == null || contenidoTexto.length() > 1000) {
            throw new IllegalArgumentException("Texto inválido");
        }

        return mensajeService.analizarMensaje(contenidoTexto);
    }
}