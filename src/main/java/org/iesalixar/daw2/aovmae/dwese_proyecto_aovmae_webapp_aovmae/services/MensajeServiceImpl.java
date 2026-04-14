package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.MensajeDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Mensaje;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers.MensajeMapper;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.MensajeRepository;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private final MensajeRepository mensajeRepository;

    public MensajeServiceImpl(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    /**
     * 🔐 Solo ADMIN o MODERATOR pueden ver TODOS los mensajes
     */
    @Override
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public List<MensajeDTO> listAll() {
        return mensajeRepository.findAll().stream()
                .map(MensajeMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * 🔐 Usuario autenticado puede analizar mensajes
     */
    @Override
    @PreAuthorize("isAuthenticated()")
    public MensajeDTO analizarMensaje(String contenidoTexto) {

        String nivelRiesgo = calcularNivelRiesgo(contenidoTexto);
        String resultadoAnalisis = "Análisis automático: " + nivelRiesgo;

        Mensaje mensaje = new Mensaje();
        mensaje.setContenidoTexto(contenidoTexto);
        mensaje.setNivelRiesgo(nivelRiesgo);
        mensaje.setResultadoAnalisis(resultadoAnalisis);
        mensaje.setFechaAnalisis(LocalDateTime.now());

        mensajeRepository.save(mensaje);

        return MensajeMapper.toDTO(mensaje);
    }

    private String calcularNivelRiesgo(String texto) {
        if (texto.length() > 200) return "ALTO";
        if (texto.length() > 100) return "MEDIO";
        if (texto.length() > 50) return "BAJO";
        return "SEGURO";
    }
}