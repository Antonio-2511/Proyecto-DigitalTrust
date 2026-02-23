package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Advertencia;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertenciaRepository extends JpaRepository<Advertencia, Long> {


    Page<Advertencia> findByEsEmergenciaTrue(Pageable pageable);

    boolean existsByTitulo(@NotBlank @Size(max = 100) String titulo);

    Page<Advertencia> findByUser(User user, Pageable pageable);

}
