package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import jakarta.servlet.http.HttpSession;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.ObjetoCarrito;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalModelAdvice {

    @ModelAttribute("cartCount")
    public int cartCount(HttpSession session) {

        List<ObjetoCarrito> carrito =
                (List<ObjetoCarrito>) session.getAttribute("carrito");

        if (carrito == null) {
            return 0;
        }

        return carrito.stream()
                .mapToInt(ObjetoCarrito::getCantidad)
                .sum();
    }
}