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

    /**
     * Repositorio para persistir las advertencias generadas tras el análisis.
     */
    private final AdvertenciaRepository advertenciaRepository;

    /**
     * Mapa de palabras clave con su peso de riesgo asociado.
     * <p>
     * Cada entrada representa una palabra potencialmente peligrosa
     * y el valor indica su impacto en el cálculo del riesgo.
     * </p>
     */
    private final Map<String, Integer> palabrasRiesgo = new HashMap<>();

    /**
     * Constructor que inicializa el repositorio y las palabras de riesgo.
     *
     * @param advertenciaRepository repositorio de advertencias
     */
    public DetectorPalabrasServiceImpl(AdvertenciaRepository advertenciaRepository) {
        this.advertenciaRepository = advertenciaRepository;

        palabrasRiesgo.put("compra", 3);
        palabrasRiesgo.put("datos", 5);
        palabrasRiesgo.put("somos", 4);
        palabrasRiesgo.put("banco", 5);
        palabrasRiesgo.put("seguro", 4);
    }

    /**
     * Analiza un texto y calcula su nivel de riesgo basado en palabras clave.
     *
     * @param texto texto a analizar
     * @return puntuación de riesgo acumulada
     */
    @Override
    public int analizarTexto(String texto) {
        if (texto == null || texto.isBlank()) return 0;

        /**
         * Normalización del texto:
         * - Conversión a minúsculas
         * - Eliminación de caracteres especiales
         */
        String normalizado = texto.toLowerCase().replaceAll("[^a-z0-9 ]", "");
        String[] tokens = normalizado.split("\\s+");

        int riesgo = 0;

        /**
         * Suma de los pesos asociados a cada palabra detectada.
         */
        for (String token : tokens) {
            riesgo += palabrasRiesgo.getOrDefault(token, 0);
        }

        return riesgo;
    }

    /**
     * Determina el nivel de criticidad a partir del riesgo calculado.
     *
     * @param riesgo puntuación obtenida del análisis
     * @return nivel de criticidad (1–5)
     *         <ul>
     *             <li>1: Seguro</li>
     *             <li>2: Bajo</li>
     *             <li>3: Medio</li>
     *             <li>5: Alto</li>
     *         </ul>
     */
    @Override
    public Integer determinarNivel(int riesgo) {
        if (riesgo >= 8) return 5;   // ALTO
        if (riesgo >= 4) return 3;   // MEDIO
        if (riesgo > 0)  return 2;   // BAJO
        return 1;                    // SEGURO
    }

    /**
     * Analiza un mensaje completo, genera una advertencia y la persiste.
     *
     * @param texto contenido del mensaje a analizar
     * @return {@link AdvertenciaDTO} con el resultado del análisis
     */
    @Override
    public AdvertenciaDTO analizarMensaje(String texto) {

        int riesgo = analizarTexto(texto);
        Integer nivel = determinarNivel(riesgo);

        /**
         * Creación de la entidad Advertencia con los resultados del análisis.
         */
        Advertencia advertencia = new Advertencia();
        advertencia.setTitulo("Mensaje Analizado");
        advertencia.setDescripcion(texto);
        advertencia.setNivelCriticidad(nivel); // ✅ ahora es Integer
        advertencia.setFechaEnvio(LocalDateTime.now());
        advertencia.setEsEmergencia(nivel >= 5);

        /**
         * Persistencia de la advertencia en base de datos.
         */
        advertenciaRepository.save(advertencia);

        return AdvertenciaMapper.toDTO(advertencia);
    }

    /**
     * Obtiene todas las advertencias almacenadas.
     *
     * @return lista de {@link AdvertenciaDTO}
     */
    public List<AdvertenciaDTO> listAll() {
        return advertenciaRepository.findAll().stream()
                .map(AdvertenciaMapper::toDTO)
                .collect(Collectors.toList());
    }
}