package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.MensajeDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.MensajeService;
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

    /**
     * Servicio encargado de la lógica de negocio para mensajes.
     */
    private final MensajeService mensajeService;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param mensajeService servicio de análisis de mensajes
     */
    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    /**
     * Endpoint que devuelve todos los mensajes registrados.
     *
     * @return lista de mensajes analizados
     */
    @GetMapping("/todos")
    public List<MensajeDTO> getAll() {
        return mensajeService.listAll();
    }

    /**
     * Endpoint para analizar un mensaje de texto.
     *
     * Recibe directamente el contenido del mensaje en el body de la petición.
     *
     * @param contenidoTexto texto a analizar
     * @return resultado del análisis del mensaje
     *
     * ⚠️ Seguridad:
     * - Validar que el texto no sea nulo o excesivamente largo.
     * - Sanitizar contenido para evitar inyecciones.
     * - Considerar autenticación si el endpoint es público.
     */
    @PostMapping("/analizar")
    public MensajeDTO analizar(@RequestBody String contenidoTexto) {
        return mensajeService.analizarMensaje(contenidoTexto);
    }
}