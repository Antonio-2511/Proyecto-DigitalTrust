package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;


import jakarta.transaction.Transactional;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteUpdateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Reporte;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.exceptions.ResourceNotFoundException;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers.ReporteMapper;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private UserService userService;

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

        ReporteUpdateDTO dto = new ReporteUpdateDTO();
        dto.setId(reporte.getId());
        dto.setTitulo(reporte.getTitulo());
        dto.setDescripcion(reporte.getDescripcion());
        return dto;
    }

    @Override
    public void create(ReporteCreateDTO dto) {

        User usuario = userService.getAuthenticatedUser();

        Reporte reporte = ReporteMapper.toEntity(dto, usuario);
        reporte.setFechaReporte(LocalDateTime.now());

        reporteRepository.save(reporte);
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
}
