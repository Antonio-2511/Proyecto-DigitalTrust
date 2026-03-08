package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.springframework.stereotype.Service;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Advertencia;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.AdvertenciaRepository;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers.AdvertenciaMapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DetectorPalabrasServiceImpl implements DetectorPalabrasService {

    private final AdvertenciaRepository advertenciaRepository;

    private final Map<String, Integer> palabrasRiesgo = new HashMap<>();

    public DetectorPalabrasServiceImpl(AdvertenciaRepository advertenciaRepository) {
        this.advertenciaRepository = advertenciaRepository;

        palabrasRiesgo.put("compra", 3);
        palabrasRiesgo.put("datos", 5);
        palabrasRiesgo.put("somos", 4);
        palabrasRiesgo.put("banco", 5);
        palabrasRiesgo.put("seguro", 4);
    }

    @Override
    public int analizarTexto(String texto) {
        if (texto == null || texto.isBlank()) return 0;

        String normalizado = texto.toLowerCase().replaceAll("[^a-z0-9 ]", "");
        String[] tokens = normalizado.split("\\s+");

        int riesgo = 0;
        for (String token : tokens) {
            riesgo += palabrasRiesgo.getOrDefault(token, 0);
        }

        return riesgo;
    }

    // Ahora devuelve un NIVEL NUMÉRICO (1–5)
    @Override
    public Integer determinarNivel(int riesgo) {
        if (riesgo >= 8) return 5;   // ALTO
        if (riesgo >= 4) return 3;   // MEDIO
        if (riesgo > 0)  return 2;   // BAJO
        return 1;                    // SEGURO
    }

    @Override
    public AdvertenciaDTO analizarMensaje(String texto) {

        int riesgo = analizarTexto(texto);
        Integer nivel = determinarNivel(riesgo);

        Advertencia advertencia = new Advertencia();
        advertencia.setTitulo("Mensaje Analizado");
        advertencia.setDescripcion(texto);
        advertencia.setNivelCriticidad(nivel); // ✅ ahora es Integer
        advertencia.setFechaEnvio(LocalDateTime.now());
        advertencia.setEsEmergencia(nivel >= 5);

        advertenciaRepository.save(advertencia);

        return AdvertenciaMapper.toDTO(advertencia);
    }

    public List<AdvertenciaDTO> listAll() {
        return advertenciaRepository.findAll().stream()
                .map(AdvertenciaMapper::toDTO)
                .collect(Collectors.toList());
    }
}