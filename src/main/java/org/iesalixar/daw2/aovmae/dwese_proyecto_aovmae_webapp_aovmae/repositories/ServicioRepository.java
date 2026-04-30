package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    @Query("SELECT s FROM Servicio s WHERE " +
            "(:nombre IS NULL OR LOWER(s.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
            "(:categoria IS NULL OR s.categoria = :categoria) AND " +
            "(:precioMax IS NULL OR s.precio <= :precioMax)")
    List<Servicio> filtrarServicios(@Param("nombre") String nombre,
                                    @Param("categoria") String categoria,
                                    @Param("precioMax") Double precioMax);
}
