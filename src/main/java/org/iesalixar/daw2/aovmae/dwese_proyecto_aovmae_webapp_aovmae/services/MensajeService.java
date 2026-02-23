package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.MensajeDTO;

import java.util.List;

public interface MensajeService {

    List<MensajeDTO> listAll();

    MensajeDTO analizarMensaje(String contenidoTexto);
}