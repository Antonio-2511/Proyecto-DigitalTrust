package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.DetectorPalabrasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen para pruebas
@RestController
@RequestMapping("/api/detector") // Mantenemos tu ruta original
public class DetectorPalabrasController {

    private final DetectorPalabrasService detectorService;

    public DetectorPalabrasController(DetectorPalabrasService detectorService) {
        this.detectorService = detectorService;
    }

    @GetMapping("/todos")
    public List<AdvertenciaDTO> getAll() {
        return detectorService.listAll();
    }

    @PostMapping("/analizar")
    public AdvertenciaDTO analizar(@RequestBody java.util.Map<String, String> payload) {
        String texto = payload.get("contenidoTexto"); // Extrae el valor del JSON { "contenidoTexto": "..." }
        return detectorService.analizarMensaje(texto != null ? texto : "");
    }
    // Clase estática para recibir el JSON {"contenidoTexto": "..."}
    public static class MensajeRequest {
        private String contenidoTexto;
        public String getContenidoTexto() { return contenidoTexto; }
        public void setContenidoTexto(String contenidoTexto) { this.contenidoTexto = contenidoTexto; }
    }
}