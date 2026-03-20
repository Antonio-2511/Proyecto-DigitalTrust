package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.DetectorPalabrasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la detección de posibles estafas en textos.
 *
 * Proporciona endpoints para:
 * - Obtener todas las advertencias registradas
 * - Analizar mensajes en busca de patrones sospechosos
 *
 * ⚠️ Seguridad:
 * Este endpoint puede ser objetivo de:
 * - Abuso de API (spam de peticiones)
 * - Envío de payloads maliciosos
 * - Ataques de denegación de servicio (DoS)
 *
 * Se recomienda implementar:
 * - Rate limiting
 * - Validación de entrada
 * - Autenticación en producción
 */
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen para pruebas
@RestController
@RequestMapping("/api/detector") // Mantenemos tu ruta original
public class DetectorPalabrasController {

    /**
     * Servicio encargado de analizar textos y detectar patrones de estafa.
     */
    private final DetectorPalabrasService detectorService;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param detectorService servicio de detección de palabras clave
     */
    public DetectorPalabrasController(DetectorPalabrasService detectorService) {
        this.detectorService = detectorService;
    }

    /**
     * Endpoint que devuelve todas las advertencias registradas.
     *
     * @return lista de advertencias
     */
    @GetMapping("/todos")
    public List<AdvertenciaDTO> getAll() {
        return detectorService.listAll();
    }

    /**
     * Endpoint para analizar un texto y detectar posibles estafas.
     *
     * Recibe un JSON con formato:
     * {
     *   "contenidoTexto": "mensaje a analizar"
     * }
     *
     * @param payload mapa con los datos recibidos en el body
     * @return advertencia generada tras el análisis
     *
     * ⚠️ Seguridad:
     * - Validar tamaño del texto (evitar payloads excesivos).
     * - Sanitizar entrada para evitar inyecciones.
     * - Considerar autenticación si se expone públicamente.
     */
    @PostMapping("/analizar")
    public AdvertenciaDTO analizar(@RequestBody java.util.Map<String, String> payload) {
        String texto = payload.get("contenidoTexto"); // Extrae el valor del JSON { "contenidoTexto": "..." }
        return detectorService.analizarMensaje(texto != null ? texto : "");
    }

    /**
     * Clase auxiliar para mapear el JSON de entrada de forma tipada.
     *
     * Representa el payload:
     * {
     *   "contenidoTexto": "..."
     * }
     *
     * ⚠️ Buenas prácticas:
     * Se recomienda usar esta clase en lugar de Map para mayor seguridad y claridad.
     */
    public static class MensajeRequest {
        private String contenidoTexto;

        /**
         * Obtiene el contenido del texto a analizar.
         *
         * @return texto del mensaje
         */
        public String getContenidoTexto() { return contenidoTexto; }

        /**
         * Establece el contenido del texto a analizar.
         *
         * @param contenidoTexto texto del mensaje
         */
        public void setContenidoTexto(String contenidoTexto) { this.contenidoTexto = contenidoTexto; }
    }
}