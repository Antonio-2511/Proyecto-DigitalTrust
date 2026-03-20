package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Advertencia;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de acceso a datos para la entidad {@link Advertencia}.
 *
 * <p>Extiende {@link JpaRepository}, proporcionando operaciones CRUD estándar
 * junto con métodos personalizados basados en el nombre del método (query methods).</p>
 *
 * <p>Permite realizar consultas específicas relacionadas con advertencias,
 * como filtrado por estado de emergencia o por usuario asociado.</p>
 *
 * <p>Las operaciones que devuelven {@link Page} permiten paginación eficiente,
 * mejorando el rendimiento en consultas con grandes volúmenes de datos.</p>
 *
 * @author
 */
@Repository
public interface AdvertenciaRepository extends JpaRepository<Advertencia, Long> {


    /**
     * Obtiene una página de advertencias marcadas como emergencia.
     *
     * @param pageable configuración de paginación
     * @return página de advertencias de emergencia
     */
    Page<Advertencia> findByEsEmergenciaTrue(Pageable pageable);

    /**
     * Verifica si existe una advertencia con el título especificado.
     *
     * <p>Se utiliza para evitar duplicados o validar unicidad antes de persistir.</p>
     *
     * @param titulo título de la advertencia
     * @return true si existe una advertencia con ese título, false en caso contrario
     */
    boolean existsByTitulo(@NotBlank @Size(max = 100) String titulo);

    /**
     * Obtiene una página de advertencias asociadas a un usuario específico.
     *
     * @param user usuario al que pertenecen las advertencias
     * @param pageable configuración de paginación
     * @return página de advertencias del usuario
     */
    Page<Advertencia> findByUser(User user, Pageable pageable);

}