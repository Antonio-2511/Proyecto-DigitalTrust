package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Servicio;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjetoCarrito {
    private Servicio servicio;
    private int cantidad;

    public double getSubtotal() {
        return servicio.getPrecio() * cantidad;
    }
}