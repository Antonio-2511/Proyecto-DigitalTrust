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

/**
 * Implementación del servicio {@link ReporteService}.
 * <p>
 * Gestiona la lógica de negocio asociada a los reportes de usuarios,
 * incluyendo operaciones CRUD y asociación con el usuario autenticado.
 * </p>
 *
 * <p>
 * Los reportes representan posibles incidencias o estafas detectadas,
 * siendo una pieza clave en el sistema de ciberseguridad.
 * </p>
 */
@Service
@Transactional
public class ReporteServiceImpl implements ReporteService {

    /**
     * Servicio de usuarios para obtener el usuario autenticado.
     */
    @Autowired
    private UserService userService;

    /**
     * Repositorio de acceso a datos para la entidad {@link Reporte}.
     */
    @Autowired
    private ReporteRepository reporteRepository;

    /**
     * Obtiene una lista paginada de reportes.
     *
     * @param pageable configuración de paginación
     * @return página de {@link ReporteDTO}
     */
    @Override
    public Page<ReporteDTO> list(Pageable pageable) {
        return reporteRepository
                .findAll(pageable)
                .map(ReporteMapper::toDTO);
    }

    /**
     * Obtiene un reporte para su edición.
     *
     * @param id identificador del reporte
     * @return {@link ReporteUpdateDTO} con los datos editables
     * @throws ResourceNotFoundException si el reporte no existe
     */
    @Override
    public ReporteUpdateDTO getForEdit(Long id) {

        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("reporte", "id", id)
                );

        return ReporteMapper.toUpdateDTO(reporte);
    }

    /**
     * Crea un nuevo reporte asociado al usuario autenticado.
     *
     * @param dto datos de creación del reporte
     */
    @Override
    public void create(ReporteCreateDTO dto) {

        /**
         * Obtención del usuario autenticado en el sistema.
         */
        User usuario = userService.getAuthenticatedUser();

        /**
         * Conversión del DTO a entidad y persistencia.
         */
        Reporte reporte = ReporteMapper.toEntity(dto, usuario);
        reporteRepository.save(reporte);
    }

    /**
     * Actualiza un reporte existente.
     *
     * @param dto datos actualizados del reporte
     * @throws ResourceNotFoundException si el reporte no existe
     */
    @Override
    public void update(ReporteUpdateDTO dto) {

        Reporte reporte = reporteRepository.findById(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("reporte", "id", dto.getId())
                );

        ReporteMapper.copyToExistingEntity(dto, reporte);
        reporteRepository.save(reporte);
    }

    /**
     * Elimina un reporte por su identificador.
     *
     * @param id identificador del reporte
     * @throws ResourceNotFoundException si el reporte no existe
     */
    @Override
    public void delete(Long id) {

        if (!reporteRepository.existsById(id)) {
            throw new ResourceNotFoundException("reporte", "id", id);
        }

        reporteRepository.deleteById(id);
    }

    /**
     * Obtiene el detalle completo de un reporte.
     *
     * @param id identificador del reporte
     * @return {@link ReporteDetailDTO} con la información detallada
     * @throws ResourceNotFoundException si el reporte no existe
     */
    @Override
    public ReporteDetailDTO getDetail(Long id) {

        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("reporte", "id", id)
                );

        return ReporteMapper.toDetailDTO(reporte);
    }
}