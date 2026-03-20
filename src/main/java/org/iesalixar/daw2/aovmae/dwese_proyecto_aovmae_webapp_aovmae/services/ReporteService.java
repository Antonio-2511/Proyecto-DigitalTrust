package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Interfaz de servicio para la gestión de reportes dentro del sistema.
 * <p>
 * Define las operaciones de negocio relacionadas con los reportes,
 * incluyendo creación, actualización, eliminación y consultas.
 * </p>
 *
 * <p>
 * Los reportes pueden representar incidencias, posibles estafas o
 * información relevante enviada por los usuarios.
 * </p>
 */
@Service
public interface ReporteService {

    /**
     * Obtiene una lista paginada de reportes.
     *
     * @param pageable objeto {@link Pageable} que define la paginación
     * @return página ({@link Page}) de {@link ReporteDTO} con los reportes
     */
    Page<ReporteDTO> list(Pageable pageable);

    /**
     * Obtiene un reporte para su edición.
     *
     * @param id identificador del reporte
     * @return {@link ReporteUpdateDTO} con los datos editables
     */
    ReporteUpdateDTO getForEdit(Long id);

    /**
     * Crea un nuevo reporte en el sistema.
     *
     * @param dto datos necesarios para la creación del reporte
     */
    void create(ReporteCreateDTO dto);

    /**
     * Actualiza un reporte existente.
     *
     * @param dto datos actualizados del reporte
     */
    void update(ReporteUpdateDTO dto);

    /**
     * Elimina un reporte del sistema.
     *
     * @param id identificador del reporte a eliminar
     */
    void delete(Long id);

    /**
     * Obtiene el detalle completo de un reporte.
     *
     * @param id identificador del reporte
     * @return {@link ReporteDetailDTO} con la información detallada
     */
    ReporteDetailDTO getDetail(Long id);
}