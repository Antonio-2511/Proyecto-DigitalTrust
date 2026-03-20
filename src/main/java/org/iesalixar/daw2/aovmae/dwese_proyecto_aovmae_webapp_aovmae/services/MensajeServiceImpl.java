package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.MensajeDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Mensaje;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers.MensajeMapper;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.MensajeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio {@link MensajeService}.
 * <p>
 * Este servicio gestiona la lógica de negocio asociada al análisis de mensajes,
 * incluyendo su evaluación de riesgo y almacenamiento.
 * </p>
 *
 * <p>
 * Utiliza un enfoque simplificado basado en la longitud del texto para determinar
 * el nivel de riesgo, lo que puede servir como base para futuros sistemas más avanzados.
 * </p>
 */
@Service
public class MensajeServiceImpl implements MensajeService {

    /**
     * Repositorio encargado de la persistencia de mensajes.
     */
    private final MensajeRepository mensajeRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param mensajeRepository repositorio de mensajes
     */
    public MensajeServiceImpl(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    /**
     * Obtiene todos los mensajes almacenados en el sistema.
     *
     * @return lista de {@link MensajeDTO} con los mensajes existentes
     */
    @Override
    public List<MensajeDTO> listAll() {
        return mensajeRepository.findAll().stream()
                .map(MensajeMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Analiza un mensaje de texto, calcula su nivel de riesgo y lo almacena.
     *
     * @param contenidoTexto contenido del mensaje a analizar
     * @return {@link MensajeDTO} con los resultados del análisis
     */
    @Override
    public MensajeDTO analizarMensaje(String contenidoTexto) {
        String nivelRiesgo = calcularNivelRiesgo(contenidoTexto);
        String resultadoAnalisis = "Análisis automático: " + nivelRiesgo;

        /**
         * Creación de la entidad Mensaje con los resultados del análisis.
         */
        Mensaje mensaje = new Mensaje();
        mensaje.setContenidoTexto(contenidoTexto);
        mensaje.setNivelRiesgo(nivelRiesgo);
        mensaje.setResultadoAnalisis(resultadoAnalisis);
        mensaje.setFechaAnalisis(LocalDateTime.now());

        /**
         * Persistencia del mensaje en el repositorio.
         */
        mensajeRepository.save(mensaje);

        return MensajeMapper.toDTO(mensaje);
    }

    /**
     * Calcula el nivel de riesgo de un mensaje en función de su longitud.
     * <p>
     * Este método utiliza un criterio básico donde textos más largos
     * se consideran potencialmente más riesgosos.
     * </p>
     *
     * @param texto contenido del mensaje
     * @return nivel de riesgo como cadena:
     *         <ul>
     *             <li>"SEGURO"</li>
     *             <li>"BAJO"</li>
     *             <li>"MEDIO"</li>
     *             <li>"ALTO"</li>
     *         </ul>
     */
    private String calcularNivelRiesgo(String texto) {
        if (texto.length() > 200) return "ALTO";
        if (texto.length() > 100) return "MEDIO";
        if (texto.length() > 50) return "BAJO";
        return "SEGURO";
    }
}