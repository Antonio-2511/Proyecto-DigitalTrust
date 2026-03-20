package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador principal de la aplicación (Home).
 *
 * Gestiona:
 * - La página de inicio (landing page)
 * - Navegación hacia funcionalidades públicas como el detector de mensajes
 *
 * Forma parte de la capa de presentación en el patrón MVC.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * Muestra la página principal de la aplicación.
     *
     * @return nombre de la vista "index"
     */
    @GetMapping
    public String index() {
        return "index"; // index.html
    }

    /**
     * Muestra la página del detector de mensajes sospechosos.
     *
     * Esta funcionalidad permite al usuario analizar textos
     * en busca de posibles patrones de estafa.
     *
     * @return nombre de la vista del detector
     */
    @GetMapping("detector-mensajes-sospechosos")
    public String detectorMensajes() {
        return "detector-mensajes-sospechosos"; // detector-mensajes-sospechosos.html
    }


    /**
     * ⚙️ Zona para futuras funcionalidades.
     *
     * Se pueden añadir nuevos endpoints públicos o privados
     * según evolucione la aplicación.
     */
    // Opcional: aquí mismo puedes poner métodos para otras funcionalidades futuras
    //Hola
}