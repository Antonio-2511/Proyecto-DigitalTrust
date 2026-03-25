package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import jakarta.transaction.Transactional;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.*;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Advertencia;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Reporte;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.exceptions.ResourceNotFoundException;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers.ReporteMapper;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.AdvertenciaRepository;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private UserService userService;

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private AdvertenciaRepository advertenciaRepository;

    @Override
    public Page<ReporteDTO> list(Pageable pageable) {
        return reporteRepository
                .findAll(pageable)
                .map(ReporteMapper::toDTO);
    }

    @Override
    public ReporteUpdateDTO getForEdit(Long id) {

        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("reporte", "id", id)
                );

        return ReporteMapper.toUpdateDTO(reporte);
    }

    @Override
    public void create(ReporteCreateDTO dto) {

        User usuario = userService.getAuthenticatedUser();

        Reporte reporte = ReporteMapper.toEntity(dto, usuario);

        // 🔥 adaptado a tu entidad
        reporte.setFechaReporte(LocalDateTime.now());

        reporteRepository.save(reporte);

        // 🔥 IA simulada (sin estado)
        comprobarReportesMasivos(reporte);
    }

    @Override
    public void update(ReporteUpdateDTO dto) {

        Reporte reporte = reporteRepository.findById(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("reporte", "id", dto.getId())
                );

        ReporteMapper.copyToExistingEntity(dto, reporte);
        reporteRepository.save(reporte);
    }

    @Override
    public void delete(Long id) {

        if (!reporteRepository.existsById(id)) {
            throw new ResourceNotFoundException("reporte", "id", id);
        }

        reporteRepository.deleteById(id);
    }

    @Override
    public ReporteDetailDTO getDetail(Long id) {

        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("reporte", "id", id)
                );

        return ReporteMapper.toDetailDTO(reporte);
    }

    // =========================================================
    // 🔥 LÓGICA DE NEGOCIO SIN MODIFICAR BD
    // =========================================================

    /**
     * Detecta si hay múltiples reportes con la misma descripción
     * y genera una advertencia automática.
     */
    private void comprobarReportesMasivos(Reporte reporte) {

        List<Reporte> similares =
                reporteRepository.findByDescripcion(reporte.getDescripcion());

        if (similares.size() == 3) {

            Advertencia advertencia = new Advertencia();

            advertencia.setTitulo("Alerta global de fraude");
            advertencia.setDescripcion("Contenido reportado varias veces: "
                    + reporte.getDescripcion());
            advertencia.setNivelCriticidad(5);
            advertencia.setFechaEnvio(LocalDateTime.now());
            advertencia.setEsEmergencia(true);

            advertenciaRepository.save(advertencia);
        }
    }

    /**
     * Validación manual (sin guardar estado en BD)
     */
    public void validarReporte(Long id, boolean esFraude) {

        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("reporte", "id", id)
                );

        if (esFraude) {
            crearAdvertenciaDesdeReporte(reporte);
        }

        // 💡 No guardamos estado → solo comportamiento
    }

    /**
     * Genera advertencia desde un reporte validado
     */
    private void crearAdvertenciaDesdeReporte(Reporte reporte) {

        Advertencia advertencia = new Advertencia();

        advertencia.setTitulo("Contenido confirmado como fraude");
        advertencia.setDescripcion(reporte.getDescripcion());
        advertencia.setNivelCriticidad(5);
        advertencia.setFechaEnvio(LocalDateTime.now());
        advertencia.setEsEmergencia(true);

        advertenciaRepository.save(advertencia);
    }
}