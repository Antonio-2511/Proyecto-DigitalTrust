package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index() {
        return "index"; // index.html
    }

    @GetMapping("detector-mensajes-sospechosos")
    public String detectorMensajes() {
        return "detector-mensajes-sospechosos"; // detector-mensajes-sospechosos.html
    }

    // Opcional: aquí mismo puedes poner métodos para otras funcionalidades futuras
}