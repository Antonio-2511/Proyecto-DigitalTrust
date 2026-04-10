package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;


import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Plan;

import java.util.List;

public interface TiendaService {
    List<Plan> listarTodos();
    Plan obtenerPorNombre(String nombre);
}