package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import jakarta.transaction.Transactional;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaUpdateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Advertencia;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.exceptions.DuplicateResourceException;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.exceptions.ResourceNotFoundException;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.mappers.AdvertenciaMapper;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.AdvertenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio {@link AdvertenciaService}.
 * <p>
 * Contiene la lógica de negocio para la gestión de advertencias,
 * incluyendo operaciones CRUD y consultas específicas como filtrado
 * de advertencias de emergencia.
 * </p>
 *
 * <p>
 * Utiliza {@link AdvertenciaRepository} para el acceso a datos y
 * {@link AdvertenciaMapper} para la conversión entre entidades y DTOs.
 * </p>
 */
@Service
@Transactional
public class AdvertenciaServiceImpl implements AdvertenciaService {

    @Autowired
    private AdvertenciaRepository advertenciaRepository;

    /**
     * 🔐 Solo MODERATOR o ADMIN pueden ver todas
     */
    @Override
    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    public Page<AdvertenciaDTO> list(Pageable pageable) {
        return advertenciaRepository.findAll(pageable)
                .map(AdvertenciaMapper::toDTO);
    }

    /**
     * 🔐 Igual que list()
     */
    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    public Page<AdvertenciaDTO> listAll(Pageable pageable) {
        return advertenciaRepository.findAll(pageable)
                .map(AdvertenciaMapper::toDTO);
    }

    /**
     * 🔐 Solo staff ve emergencias
     */
    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    public Page<AdvertenciaDTO> listEmergencias(Pageable pageable) {
        return advertenciaRepository.findByEsEmergenciaTrue(pageable)
                .map(AdvertenciaMapper::toDTO);
    }

    /**
     * 🔐 Solo ADMIN edita
     */
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public AdvertenciaUpdateDTO getForEdit(Long id) {
        Advertencia advertencia = advertenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("advertencia", "id", id));
        return AdvertenciaMapper.toUpdateDTO(advertencia);
    }

    /**
     * 🔐 SOLO ADMIN crea manualmente
     * (el sistema ya crea advertencias automáticamente)
     */
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void create(AdvertenciaCreateDTO dto) {

        if (advertenciaRepository.existsByTitulo(dto.getTitulo())) {
            throw new DuplicateResourceException("advertencia", "titulo", dto.getTitulo());
        }

        Advertencia advertencia = AdvertenciaMapper.toEntity(dto, null);

        advertenciaRepository.save(advertencia);
    }

    /**
     * 🔐 SOLO ADMIN
     */
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void update(AdvertenciaUpdateDTO dto) {
        Advertencia advertencia = advertenciaRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("advertencia", "id", dto.getId()));

        AdvertenciaMapper.copyToExistingEntity(dto, advertencia);
        advertenciaRepository.save(advertencia);
    }

    /**
     * 🔐 SOLO ADMIN
     */
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        if (!advertenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("advertencia", "id", id);
        }
        advertenciaRepository.deleteById(id);
    }

    /**
     * 🔐 Usuario autenticado puede ver detalle (si decides abrirlo)
     */
    @Override
    @PreAuthorize("isAuthenticated()")
    public AdvertenciaDetailDTO getDetail(Long id) {
        Advertencia advertencia = advertenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("advertencia", "id", id));
        return AdvertenciaMapper.toDetailDTO(advertencia);
    }
}