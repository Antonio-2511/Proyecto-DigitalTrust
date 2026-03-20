package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Mensaje;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio en memoria para la gestión de objetos {@link Mensaje}.
 * <p>
 * Esta clase simula el comportamiento de una capa de persistencia,
 * almacenando los mensajes en una lista en memoria.
 * Está anotada con {@link Repository} para que Spring la detecte
 * como componente de acceso a datos.
 * </p>
 *
 * ⚠️ Nota: Este repositorio no utiliza base de datos, por lo que
 * los datos se perderán al reiniciar la aplicación.
 */
@Repository
public class MensajeRepository {

    /**
     * Lista interna que almacena los mensajes en memoria.
     */
    private final List<Mensaje> mensajes = new ArrayList<>();

    /**
     * Guarda un mensaje en el repositorio.
     * <p>
     * El mensaje se añade a la lista interna simulando una operación de persistencia.
     * </p>
     *
     * @param mensaje el objeto {@link Mensaje} que se desea guardar
     * @return el mismo objeto {@link Mensaje} guardado
     */
    public Mensaje save(Mensaje mensaje) {
        mensajes.add(mensaje);
        return mensaje;
    }

    /**
     * Obtiene todos los mensajes almacenados.
     * <p>
     * Devuelve una copia de la lista interna para evitar modificaciones externas.
     * </p>
     *
     * @return una lista de objetos {@link Mensaje} con todos los mensajes almacenados
     */
    public List<Mensaje> findAll() {
        return new ArrayList<>(mensajes);
    }
}