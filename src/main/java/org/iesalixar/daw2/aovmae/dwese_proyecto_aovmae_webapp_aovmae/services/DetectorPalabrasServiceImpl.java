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

        // Diccionario de palabras y sus riesgos
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

    @Override
    public String determinarNivel(int riesgo) {
        if (riesgo >= 8) return "ALTO";
        if (riesgo >= 4) return "MEDIO";
        if (riesgo > 0) return "BAJO";
        return "SEGURO";
    }

    @Override
    public AdvertenciaDTO analizarMensaje(String texto) {
        int riesgo = analizarTexto(texto);
        String nivel = determinarNivel(riesgo);

        // Guardamos la advertencia en la base de datos
        Advertencia advertencia = new Advertencia();
        advertencia.setTitulo("Mensaje Analizado");
        advertencia.setDescripcion(texto);
        advertencia.setNivelCriticidad(nivel);
        advertencia.setFechaEnvio(LocalDateTime.now());
        advertencia.setEsEmergencia(riesgo >= 8);

        // IMPORTANTE: El usuario y fuente deben asignarse según tu sistema
        // Por ahora podemos usar null si se permite, o un usuario/fuente temporal si tu DB lo requiere
        // advertencia.setUsuario(usuario);
        // advertencia.setFuenteConfiable(fuente);

        advertenciaRepository.save(advertencia);

        // Convertimos a DTO
        AdvertenciaDTO dto = AdvertenciaMapper.toDTO(advertencia);
        dto.setNivelCriticidad(nivel); // sobrescribimos con el nivel en palabras
        return dto;
    }

    public List<AdvertenciaDTO> listAll() {
        return advertenciaRepository.findAll().stream()
                .map(AdvertenciaMapper::toDTO)
                .collect(Collectors.toList());
    }
}