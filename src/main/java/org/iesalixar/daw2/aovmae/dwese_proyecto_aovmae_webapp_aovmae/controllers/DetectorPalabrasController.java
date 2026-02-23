package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.DetectorPalabrasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detector")
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
    public AdvertenciaDTO analizar(@RequestBody String texto) {
        return detectorService.analizarMensaje(texto);
    }
}