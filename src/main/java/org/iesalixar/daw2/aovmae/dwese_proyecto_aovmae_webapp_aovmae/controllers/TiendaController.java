package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Servicio;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tienda")
public class TiendaController {

    @Autowired
    private ServicioRepository servicioRepository;

    /**
     * Muestra la tienda principal con sistema de filtros.
     * * @param q       Búsqueda por nombre (opcional)
     * @param categoria Búsqueda por categoría (opcional)
     * @param precio  Filtro de precio máximo (opcional)
     */
    @GetMapping
    public String mostrarTienda(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Double precio,
            Model model) {

        // Limpieza de parámetros para evitar que las cadenas vacías filtren
        String queryParam = (q != null && !q.trim().isEmpty()) ? q.trim() : null;
        String catParam = (categoria != null && !categoria.trim().isEmpty()) ? categoria.trim() : null;

        List<Servicio> servicios;

        // Si no hay ningún filtro real, mostramos todo
        if (queryParam == null && catParam == null && precio == null) {
            servicios = servicioRepository.findAll();
        } else {
            servicios = servicioRepository.filtrarServicios(queryParam, catParam, precio);
        }

        model.addAttribute("servicios", servicios);
        model.addAttribute("query", q);
        model.addAttribute("catSeleccionada", categoria);
        model.addAttribute("precioSeleccionado", precio);

        return "views/Tienda/tienda";
    }

    /**
     * Muestra el detalle de un producto específico.
     * La URL será /tienda/producto/{id}
     */
    @GetMapping("/producto/{id}")
    public String verDetalleProducto(@PathVariable("id") Integer id, Model model) {
        // Buscamos el servicio por su ID
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + id));

        // Pasamos el objeto 's' (así lo llamamos en el HTML del detalle)
        model.addAttribute("s", servicio);

        // IMPORTANTE: También pasamos la lista completa de servicios para que
        // el buscador del header (JavaScript) siga funcionando en esta página
        model.addAttribute("servicios", servicioRepository.findAll());

        return "views/Tienda/detalle-producto";
    }
}