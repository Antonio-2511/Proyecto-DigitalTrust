package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.springframework.ui.Model;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tienda")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @GetMapping
    public String verTienda(Model model) {
        // Obtenemos los planes definidos en tu data.sql
        model.addAttribute("planes", tiendaService.listarTodos());
        return "views/Tienda/tienda";    }
}