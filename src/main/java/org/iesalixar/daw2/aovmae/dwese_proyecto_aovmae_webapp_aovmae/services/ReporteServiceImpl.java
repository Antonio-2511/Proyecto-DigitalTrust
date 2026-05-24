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
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * 🔐 Solo MODERATOR o ADMIN pueden ver TODOS los reportes
     */
    @Override
    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    public Page<ReporteDTO> list(Pageable pageable) {
        return reporteRepository
                .findAll(pageable)
                .map(ReporteMapper::toDTO);
    }

    /**
     * 🔐 Solo el dueño o ADMIN puede editar
     */
    @Override
    @PreAuthorize("@reporteSecurity.esPropietario(#id, authentication.name) or hasRole('ADMIN')")
    public ReporteUpdateDTO getForEdit(Long id) {

        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("reporte", "id", id)
                );

        return ReporteMapper.toUpdateDTO(reporte);
    }

    /**
     * 🔐 Cualquier usuario autenticado puede crear reportes
     */
    @Override
    @PreAuthorize("isAuthenticated()")
    public void create(ReporteCreateDTO dto) {

        User usuario = userService.getAuthenticatedUser();

        Reporte reporte = ReporteMapper.toEntity(dto, usuario);
        reporte.setFechaReporte(LocalDateTime.now());

        reporteRepository.save(reporte);

        comprobarReportesMasivos(reporte);
    }

    /**
     * 🔐 Solo propietario o ADMIN
     */
    @Override
    @PreAuthorize("#dto.id != null and (@reporteSecurity.esPropietario(#dto.id, authentication.name) or hasRole('ADMIN'))")
    public void update(ReporteUpdateDTO dto) {

        /**
         * Recuperamos el reporte
         */
        Reporte reporte = reporteRepository.findById(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("reporte", "id", dto.getId())
                );

        /**
         * 🔐 DEFENSA EXTRA (recomendado en backend crítico)
         */
        User user = userService.getAuthenticatedUser();

        boolean esPropietario = reporte.getUser().getUsername().equals(user.getUsername());
        boolean esAdmin = userService.isAdmin();

        if (!esPropietario && !esAdmin) {
            throw new RuntimeException("No tienes permisos para modificar este reporte");
        }

        /**
         * Actualización
         */
        ReporteMapper.copyToExistingEntity(dto, reporte);

        reporteRepository.save(reporte);
    }

    /**
     * 🔐 Solo MODERATOR o ADMIN pueden eliminar
     */
    @Override
    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    public void delete(Long id) {

        if (!reporteRepository.existsById(id)) {
            throw new ResourceNotFoundException("reporte", "id", id);
        }

        reporteRepository.deleteById(id);
    }

    /**
     * 🔐 Solo propietario o ADMIN
     */
    @Override
    @PreAuthorize("@reporteSecurity.esPropietario(#id, authentication.name) or hasRole('ADMIN')")
    public ReporteDetailDTO getDetail(Long id) {

        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("reporte", "id", id)
                );

        return ReporteMapper.toDetailDTO(reporte);
    }

    // =========================================================
    // 🔥 LÓGICA DE NEGOCIO
    // =========================================================

    private void comprobarReportesMasivos(Reporte reporte) {

        List<Reporte> similares =
                reporteRepository.findByDescripcion(reporte.getDescripcion());

        if (similares.size() >= 3) {

            Advertencia advertencia = new Advertencia();
            advertencia.setTitulo("Alerta global de fraude");
            advertencia.setDescripcion("Contenido reportado varias veces: "
                    + reporte.getDescripcion());
            advertencia.setNivelCriticidad(5);
            advertencia.setFechaEnvio(LocalDateTime.now());
            advertencia.setEsEmergencia(true);
            advertencia.setReporte(reporte);
            advertencia.setUser(reporte.getUser());

            advertenciaRepository.save(advertencia);
        }
    }

    @Override
    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    public void validarReporte(Long id, boolean esFraude) {

        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("reporte", "id", id)
                );

        if (reporte.isValidado()) {
            return;
        }

        reporte.setValidado(true);
        reporte.setEsFraude(esFraude);
        reporteRepository.save(reporte);

        if (esFraude) {
            crearAdvertenciaDesdeReporte(reporte);
        }
    }

    private void crearAdvertenciaDesdeReporte(Reporte reporte) {

        Advertencia advertencia = new Advertencia();
        advertencia.setTitulo(reporte.getTitulo());
        advertencia.setDescripcion(reporte.getDescripcion());
        advertencia.setNivelCriticidad(5);
        advertencia.setFechaEnvio(LocalDateTime.now());
        advertencia.setEsEmergencia(true);
        advertencia.setReporte(reporte);
        advertencia.setUser(reporte.getUser());

        advertenciaRepository.save(advertencia);
    }

    @Override
    @PreAuthorize("#username == authentication.name or hasAnyRole('MODERATOR','ADMIN')")
    public Page<ReporteDTO> listByUser(String username, Pageable pageable) {
        return reporteRepository.findByUser_Username(username, pageable)
                .map(ReporteMapper::toDTO);
    }
}