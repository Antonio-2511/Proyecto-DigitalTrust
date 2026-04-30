package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;


import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.ObjetoCarrito;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Servicio;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping
    public String verCarrito(HttpSession session, Model model) {
        List<ObjetoCarrito> carrito = obtenerCarrito(session);
        double total = carrito.stream().mapToDouble(ObjetoCarrito::getSubtotal).sum();

        model.addAttribute("items", carrito);
        model.addAttribute("total", total);
        return "views/Tienda/carrito";
    }

    @PostMapping("/add/{id}")
    public String añadirAlCarrito(@PathVariable Integer id, HttpSession session, @RequestHeader(value = "referer", required = false) String referer) {
        List<ObjetoCarrito> carrito = obtenerCarrito(session);

        // Lógica para buscar si ya existe el item
        boolean encontrado = false;
        for (ObjetoCarrito item : carrito) {
            if (item.getServicio().getId().equals(id)) {
                item.setCantidad(item.getCantidad() + 1);
                encontrado = true;
                break;
            }
        }

        // Si no existía, lo buscamos en la BD y lo añadimos
        if (!encontrado) {
            Servicio s = servicioRepository.findById(id).orElse(null);
            if (s != null) {
                carrito.add(new ObjetoCarrito(s, 1));
            }
        }

        // Redirigir a la página de la que venía (Tienda o Detalle)
        return "redirect:" + (referer != null ? referer : "/tienda");
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDelCarrito(@PathVariable Integer id, HttpSession session) {
        List<ObjetoCarrito> carrito = obtenerCarrito(session);
        carrito.removeIf(item -> item.getServicio().getId().equals(id));
        return "redirect:/carrito";
    }

    // Método de utilidad para recuperar el carrito de la sesión
    private List<ObjetoCarrito> obtenerCarrito(HttpSession session) {
        List<ObjetoCarrito> carrito = (List<ObjetoCarrito>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }
        return carrito;
    }
}