package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteDetailDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ReporteService {

    Page<ReporteDTO> list(Pageable pageable);

    ReporteUpdateDTO getForEdit(Long id);

    void create(ReporteCreateDTO dto);

    void update(ReporteUpdateDTO dto);

    void delete(Long id);

    ReporteDetailDTO getDetail(Long id);
}
