package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contacto") // Todas las rutas aquí empezarán por /contacto
public class ContactoController {

    // 1. Muestra la página de contacto
    @GetMapping
    public String mostrarPaginaContacto() {
        return "contactanos"; // Nombre de tu archivo HTML en templates
    }

    // 2. Opcional: Procesa el envío del formulario (cuando pulses "Enviar")
    @PostMapping("/enviar")
    public String procesarFormulario(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String mensaje,
            RedirectAttributes redirectAttributes) {

        // Aquí podrías enviar un email o guardar en BD
        System.out.println("Mensaje recibido de: " + nombre + " (" + email + ")");

        // Enviamos un mensaje de éxito a la vista
        redirectAttributes.addFlashAttribute("mensajeExito", "¡Gracias! Nos pondremos en contacto pronto.");

        return "redirect:/contacto";
    }
}
