package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Mensaje;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MensajeRepository {

    private final List<Mensaje> mensajes = new ArrayList<>();

    public Mensaje save(Mensaje mensaje) {
        mensajes.add(mensaje);
        return mensaje;
    }

    public List<Mensaje> findAll() {
        return new ArrayList<>(mensajes);
    }
}
