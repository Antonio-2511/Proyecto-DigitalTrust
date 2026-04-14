package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Advertencia;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.FuenteConfiable;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Mensaje;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnalisisServiceImpl implements AnalisisService {

    /**
     * 🔐 Solo usuarios autenticados pueden lanzar análisis
     * (esto normalmente lo llamará otro servicio/controlador)
     */
    @Override
    @PreAuthorize("isAuthenticated()")
    public Advertencia analizarMensaje(Mensaje mensaje, FuenteConfiable fuente) {

        // ⚠️ Ejemplo básico de implementación (puedes mejorar lógica luego)

        Advertencia advertencia = new Advertencia();

        advertencia.setTitulo("Resultado de análisis");
        advertencia.setDescripcion(mensaje.getContenidoTexto());
        advertencia.setNivelCriticidad(3); // placeholder
        advertencia.setFechaEnvio(LocalDateTime.now());
        advertencia.setEsEmergencia(false);

        // 💡 Aquí podrías integrar DetectorPalabrasService
        // o futuros modelos ML

        return advertencia;
    }
}