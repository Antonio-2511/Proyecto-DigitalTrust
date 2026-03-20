package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador encargado de la gestión del formulario de contacto.
 *
 * Gestiona:
 * - Visualización de la página de contacto
 * - Procesamiento del formulario enviado por el usuario
 *
 * ⚠️ Seguridad:
 * Este tipo de formularios es un punto común de ataque:
 * - Spam
 * - Inyección de contenido
 * - Envío de datos maliciosos
 *
 * Se recomienda aplicar validación y sanitización de entradas.
 */
@Controller
@RequestMapping("/contacto") // Todas las rutas aquí empezarán por /contacto
public class ContactoController {

    /**
     * Muestra la página de contacto.
     *
     * @return nombre de la vista HTML del formulario de contacto
     */
    @GetMapping
    public String mostrarPaginaContacto() {
        return "contactanos"; // Nombre de tu archivo HTML en templates
    }

    /**
     * Procesa el envío del formulario de contacto.
     *
     * Recibe los datos introducidos por el usuario y permite:
     * - Procesarlos (por ejemplo, enviar email o guardar en base de datos)
     * - Notificar al usuario del resultado mediante mensajes flash
     *
     * @param nombre nombre del usuario
     * @param email correo electrónico del usuario
     * @param mensaje contenido del mensaje
     * @param redirectAttributes atributos para redirección (mensajes temporales)
     * @return redirección a la página de contacto
     *
     * ⚠️ Seguridad:
     * - Validar formato de email.
     * - Limitar longitud de mensaje (prevención de ataques DoS).
     * - Sanitizar entrada para evitar XSS.
     * - Implementar CAPTCHA o rate limiting para evitar spam.
     */
    @PostMapping("/enviar")
    public String procesarFormulario(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String mensaje,
            RedirectAttributes redirectAttributes) {

        /**
         * ⚠️ SOLO PARA DESARROLLO:
         * Se imprime el mensaje en consola.
         *
         * En producción:
         * - Enviar email seguro (SMTP con autenticación)
         * - O almacenar en base de datos
         */
        System.out.println("Mensaje recibido de: " + nombre + " (" + email + ")");

        /**
         * Añade un mensaje flash para mostrar al usuario tras la redirección.
         *
         * Los atributos flash solo viven durante una petición.
         */
        redirectAttributes.addFlashAttribute("mensajeExito", "¡Gracias! Nos pondremos en contacto pronto.");

        return "redirect:/contacto";
    }
}