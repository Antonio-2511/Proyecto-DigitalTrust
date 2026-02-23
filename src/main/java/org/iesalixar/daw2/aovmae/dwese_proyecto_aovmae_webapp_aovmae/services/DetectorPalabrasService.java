package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaDTO;
import java.util.List;

public interface DetectorPalabrasService {

    /**
     * Analiza un mensaje y guarda la advertencia en la base de datos
     */
    AdvertenciaDTO analizarMensaje(String texto);

    /**
     * Lista todas las advertencias existentes
     */
    List<AdvertenciaDTO> listAll();

    /**
     * Analiza un texto y devuelve un valor de riesgo numérico
     */
    int analizarTexto(String texto);

    /**
     * Determina el nivel de riesgo en formato String
     */
    String determinarNivel(int riesgo);
}