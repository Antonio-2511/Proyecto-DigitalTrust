package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador encargado de la autenticación y recuperación de contraseña.
 *
 * Gestiona:
 * - Login de usuarios
 * - Solicitud de recuperación de contraseña
 * - Restablecimiento de contraseña mediante token
 *
 * ⚠️ Seguridad (crítico en apps anti-estafas):
 * Este controlador maneja flujos sensibles relacionados con credenciales,
 * por lo que debe protegerse contra:
 * - Enumeración de usuarios
 * - Uso indebido de tokens
 * - Exposición de enlaces de recuperación
 */
@Controller
public class AuthController {

    /**
     * Servicio encargado de la lógica de recuperación de contraseñas.
     */
    @Autowired
    private PasswordResetService resetService;

    /**
     * Muestra la página de login.
     *
     * @return vista de login
     */
    @GetMapping("/login")
    public String login() {
        return "views/login/login";
    }

    /**
     * Muestra el formulario para solicitar recuperación de contraseña.
     *
     * @return vista de "forgot password"
     */
    @GetMapping("/forgot-password")
    public String forgotPasswordForm() {
        return "views/forgot-password/forgot-password";
    }

    /**
     * Procesa la solicitud de recuperación de contraseña.
     *
     * Genera un token asociado al email del usuario y crea un enlace
     * para restablecer la contraseña.
     *
     * @param email correo electrónico del usuario
     * @return redirección al login con indicador de envío
     *
     * ⚠️ Seguridad:
     * - No debería revelarse si el email existe o no.
     * - El token debe ser:
     *   - Aleatorio
     *   - De un solo uso
     *   - Con expiración
     */
    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email) {

        String token = resetService.createToken(email);

        /**
         * ⚠️ SOLO PARA DESARROLLO:
         * Se imprime el enlace de recuperación en consola.
         *
         * En producción debe enviarse por email y nunca exponerse en logs.
         */
        System.out.println("LINK: http://localhost:8080/reset-password?token=" + token);

        return "redirect:/login?resetSent";
    }

    /**
     * Muestra el formulario para restablecer la contraseña.
     *
     * @param token token de recuperación recibido
     * @param model modelo de la vista
     * @return vista de reset de contraseña
     *
     * ⚠️ Seguridad:
     * Validar el token antes de mostrar el formulario (expiración/validez).
     */
    @GetMapping("/reset-password")
    public String resetPasswordForm(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "views/reset-password/reset-password";
    }

    /**
     * Procesa el cambio de contraseña.
     *
     * @param token token de recuperación
     * @param password nueva contraseña
     * @return redirección al login con indicador de éxito
     *
     * ⚠️ Seguridad:
     * - Validar el token (existencia, expiración, uso único).
     * - Aplicar políticas de contraseñas seguras.
     * - Codificar la contraseña antes de almacenarla.
     */
    @PostMapping("/reset-password")
    public String processResetPassword(
            @RequestParam String token,
            @RequestParam String password) {

        resetService.resetPassword(token, password);

        return "redirect:/login?resetSuccess";
    }
}