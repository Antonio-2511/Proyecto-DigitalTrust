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

import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdvertenciaServiceImpl implements AdvertenciaService {

    @Autowired
    private AdvertenciaRepository advertenciaRepository;

    @Override
    public Page<AdvertenciaDTO> list(Pageable pageable) {
        return advertenciaRepository.findAll(pageable)
                .map(AdvertenciaMapper::toDTO);
    }

    public Page<AdvertenciaDTO> listAll(Pageable pageable) {
        return advertenciaRepository.findAll(pageable)
                .map(AdvertenciaMapper::toDTO);
    }

    /**
     * Lista las advertencias marcadas como emergencia
     */
    public Page<AdvertenciaDTO> listEmergencias(Pageable pageable) {
        return advertenciaRepository.findByEsEmergenciaTrue(pageable)
                .map(AdvertenciaMapper::toDTO);
    }

    @Override
    public AdvertenciaUpdateDTO getForEdit(Long id) {
        Advertencia advertencia = advertenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("advertencia", "id", id));
        return AdvertenciaMapper.toUpdateDTO(advertencia);
    }

    @Override
    public void create(AdvertenciaCreateDTO dto) {
        if (advertenciaRepository.existsByTitulo(dto.getTitulo())) {
            throw new DuplicateResourceException("advertencia", "titulo", dto.getTitulo());
        }

        // No usamos usuario autenticado, asignamos null o un usuario genérico
        User usuario = null; // o un usuario por defecto si la entidad lo requiere

        // Convertir DTO a entidad y asignar usuario
        Advertencia advertencia = AdvertenciaMapper.toEntity(dto, usuario);

        // Guardar en la base de datos
        advertenciaRepository.save(advertencia);
    }
    @Override
    public void update(AdvertenciaUpdateDTO dto) {
        Advertencia advertencia = advertenciaRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("advertencia", "id", dto.getId()));

        AdvertenciaMapper.copyToExistingEntity(dto, advertencia);
        advertenciaRepository.save(advertencia);
    }

    @Override
    public void delete(Long id) {
        if (!advertenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("advertencia", "id", id);
        }
        advertenciaRepository.deleteById(id);
    }

    @Override
    public AdvertenciaDetailDTO getDetail(Long id) {
        Advertencia advertencia = advertenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("advertencia", "id", id));
        return AdvertenciaMapper.toDetailDTO(advertencia);
    }


    @Autowired
    private org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.UserRepository userRepository;
}