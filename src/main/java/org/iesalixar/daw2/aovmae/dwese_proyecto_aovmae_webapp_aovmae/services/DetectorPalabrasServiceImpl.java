package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
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

/**
 * Implementación del servicio {@link DetectorPalabrasService}.
 * <p>
 * Este servicio analiza textos en busca de palabras clave asociadas a posibles
 * estafas o comportamientos sospechosos, asignando un nivel de riesgo.
 * </p>
 *
 * <p>
 * Se basa en un sistema simple de puntuación donde cada palabra clave tiene
 * un peso asociado. La suma de estos pesos determina el nivel de criticidad.
 * </p>
 *
 * 💡 Este enfoque es útil como primera capa de detección en sistemas
 * anti-phishing o anti-fraude.
 */
@Service
public class DetectorPalabrasServiceImpl implements DetectorPalabrasService {

    private final AdvertenciaRepository advertenciaRepository;
    private final UserRepository userRepository;

    private final Map<String, Integer> palabrasRiesgo = new HashMap<>();

    public DetectorPalabrasServiceImpl(AdvertenciaRepository advertenciaRepository,
                                       UserRepository userRepository) {
        this.advertenciaRepository = advertenciaRepository;
        this.userRepository = userRepository;

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
    public Integer determinarNivel(int riesgo) {
        if (riesgo >= 8) return 5;
        if (riesgo >= 4) return 3;
        if (riesgo > 0)  return 2;
        return 1;
    }

    /**
     * 🔐 SOLO USUARIOS AUTENTICADOS
     *
     * 👉 Punto crítico del sistema:
     * - Entrada de datos
     * - Generación de advertencias
     * - Persistencia
     */
    @Override
    @PreAuthorize("isAuthenticated()")
    public AdvertenciaDTO analizarMensaje(String texto, String username) {

        int riesgo = analizarTexto(texto);
        Integer nivel = determinarNivel(riesgo);

        Advertencia advertencia = new Advertencia();
        advertencia.setTitulo("Análisis de Seguridad");
        advertencia.setDescripcion(texto);
        advertencia.setNivelCriticidad(nivel);
        advertencia.setFechaEnvio(LocalDateTime.now());
        advertencia.setEsEmergencia(nivel >= 5);

        User usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));

        advertencia.setUser(usuario);

        advertenciaRepository.save(advertencia);

        return AdvertenciaMapper.toDTO(advertencia);
    }

    /**
     * 🔐 SOLO STAFF puede ver TODAS las advertencias
     */
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public List<AdvertenciaDTO> listAll() {
        return advertenciaRepository.findAll().stream()
                .map(AdvertenciaMapper::toDTO)
                .collect(Collectors.toList());
    }
}