package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Advertencia;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.FuenteConfiable;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Mensaje;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnalisisServiceImpl implements AnalisisService {

    @Override
    public Advertencia analizarMensaje(Mensaje mensaje, FuenteConfiable fuente) {
        return null;
    }
}
