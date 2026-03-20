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

    /**
     * Repositorio de acceso a datos para la entidad {@link Advertencia}.
     */
    @Autowired
    private AdvertenciaRepository advertenciaRepository;

    /**
     * Obtiene una lista paginada de advertencias.
     *
     * @param pageable configuración de paginación
     * @return página de {@link AdvertenciaDTO}
     */
    @Override
    public Page<AdvertenciaDTO> list(Pageable pageable) {
        return advertenciaRepository.findAll(pageable)
                .map(AdvertenciaMapper::toDTO);
    }

    /**
     * Obtiene todas las advertencias en formato paginado.
     * <p>
     * Método equivalente a {@link #list(Pageable)}.
     * </p>
     *
     * @param pageable configuración de paginación
     * @return página de {@link AdvertenciaDTO}
     */
    public Page<AdvertenciaDTO> listAll(Pageable pageable) {
        return advertenciaRepository.findAll(pageable)
                .map(AdvertenciaMapper::toDTO);
    }

    /**
     * Lista las advertencias marcadas como emergencia.
     *
     * @param pageable configuración de paginación
     * @return página de {@link AdvertenciaDTO} filtradas por emergencia
     */
    public Page<AdvertenciaDTO> listEmergencias(Pageable pageable) {
        return advertenciaRepository.findByEsEmergenciaTrue(pageable)
                .map(AdvertenciaMapper::toDTO);
    }

    /**
     * Obtiene una advertencia para su edición.
     *
     * @param id identificador de la advertencia
     * @return {@link AdvertenciaUpdateDTO} con los datos editables
     * @throws ResourceNotFoundException si no existe la advertencia
     */
    @Override
    public AdvertenciaUpdateDTO getForEdit(Long id) {
        Advertencia advertencia = advertenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("advertencia", "id", id));
        return AdvertenciaMapper.toUpdateDTO(advertencia);
    }

    /**
     * Crea una nueva advertencia.
     * <p>
     * Verifica previamente que no exista otra advertencia con el mismo título.
     * </p>
     *
     * @param dto datos de creación
     * @throws DuplicateResourceException si ya existe una advertencia con el mismo título
     */
    @Override
    public void create(AdvertenciaCreateDTO dto) {
        if (advertenciaRepository.existsByTitulo(dto.getTitulo())) {
            throw new DuplicateResourceException("advertencia", "titulo", dto.getTitulo());
        }

        // No usamos usuario autenticado, asignamos null o un usuario genérico
        User usuario = null; // o un usuario por defecto si la entidad lo requiere

        /**
         * Conversión del DTO a entidad y asignación de usuario.
         */
        Advertencia advertencia = AdvertenciaMapper.toEntity(dto, usuario);

        /**
         * Persistencia de la entidad en base de datos.
         */
        advertenciaRepository.save(advertencia);
    }

    /**
     * Actualiza una advertencia existente.
     *
     * @param dto datos actualizados de la advertencia
     * @throws ResourceNotFoundException si la advertencia no existe
     */
    @Override
    public void update(AdvertenciaUpdateDTO dto) {
        Advertencia advertencia = advertenciaRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("advertencia", "id", dto.getId()));

        AdvertenciaMapper.copyToExistingEntity(dto, advertencia);
        advertenciaRepository.save(advertencia);
    }

    /**
     * Elimina una advertencia por su identificador.
     *
     * @param id identificador de la advertencia
     * @throws ResourceNotFoundException si no existe la advertencia
     */
    @Override
    public void delete(Long id) {
        if (!advertenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("advertencia", "id", id);
        }
        advertenciaRepository.deleteById(id);
    }

    /**
     * Obtiene el detalle completo de una advertencia.
     *
     * @param id identificador de la advertencia
     * @return {@link AdvertenciaDetailDTO} con la información detallada
     * @throws ResourceNotFoundException si no existe la advertencia
     */
    @Override
    public AdvertenciaDetailDTO getDetail(Long id) {
        Advertencia advertencia = advertenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("advertencia", "id", id));
        return AdvertenciaMapper.toDetailDTO(advertencia);
    }

    /**
     * Repositorio de usuarios (inyectado pero no utilizado actualmente).
     * <p>
     * Puede emplearse en futuras mejoras para asociar advertencias
     * a usuarios autenticados.
     * </p>
     */
    @Autowired
    private org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.UserRepository userRepository;
}