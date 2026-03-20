package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interfaz de servicio para la gestión de advertencias dentro del sistema.
 * <p>
 * Define las operaciones de negocio relacionadas con las advertencias,
 * incluyendo creación, actualización, eliminación y consulta.
 * </p>
 *
 * <p>
 * Utiliza diferentes DTOs para separar las responsabilidades según el contexto:
 * creación, edición, listado y detalle.
 * </p>
 */
public interface AdvertenciaService {

    /**
     * Obtiene una lista paginada de advertencias.
     *
     * @param pageable objeto {@link Pageable} que contiene la información de paginación
     * @return una página ({@link Page}) de {@link AdvertenciaDTO} con las advertencias
     */
    Page<AdvertenciaDTO> list(Pageable pageable);

    /**
     * Obtiene los datos de una advertencia para su edición.
     *
     * @param id identificador único de la advertencia
     * @return un {@link AdvertenciaUpdateDTO} con los datos necesarios para editar
     */
    AdvertenciaUpdateDTO getForEdit(Long id);

    /**
     * Crea una nueva advertencia en el sistema.
     *
     * @param dto objeto {@link AdvertenciaCreateDTO} con los datos necesarios para la creación
     */
    void create(AdvertenciaCreateDTO dto);

    /**
     * Actualiza una advertencia existente.
     *
     * @param dto objeto {@link AdvertenciaUpdateDTO} con los datos actualizados
     */
    void update(AdvertenciaUpdateDTO dto);

    /**
     * Elimina una advertencia del sistema.
     *
     * @param id identificador único de la advertencia a eliminar
     */
    void delete(Long id);

    /**
     * Obtiene el detalle completo de una advertencia.
     *
     * @param id identificador único de la advertencia
     * @return un {@link AdvertenciaDetailDTO} con toda la información detallada
     */
    AdvertenciaDetailDTO getDetail(Long id);
}