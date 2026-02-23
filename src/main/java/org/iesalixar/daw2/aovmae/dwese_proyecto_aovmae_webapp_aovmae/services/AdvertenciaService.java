package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdvertenciaService {

    Page<AdvertenciaDTO> list(Pageable pageable);

    AdvertenciaUpdateDTO getForEdit(Long id);

    void create(AdvertenciaCreateDTO dto);

    void update(AdvertenciaUpdateDTO dto);

    void delete(Long id);

    AdvertenciaDetailDTO getDetail(Long id);
}
