package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.security;

import org.springframework.stereotype.Component;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.ReporteRepository;

@Component("reporteSecurity")
public class ReporteSecurity {

    private final ReporteRepository reporteRepository;

    public ReporteSecurity(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    public boolean esPropietario(Long id, String username) {

        return reporteRepository.findById(id)
                .map(r -> r.getUser().getUsername().equals(username))
                .orElse(false);
    }
}