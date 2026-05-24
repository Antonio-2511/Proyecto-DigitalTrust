package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private PasswordResetService resetService;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.base-url}")
    private String baseUrl;

    @GetMapping("/login")
    public String login() {
        return "views/login/login";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordForm() {
        return "views/forgot-password/forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email, Model model) {
        try {
            String token = resetService.createToken(email);
            String link = baseUrl + "/reset-password?token=" + token;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Recuperación de contraseña - DigitalTrust");
            message.setText("Haz clic en el siguiente enlace para restablecer tu contraseña:\n\n"
                    + link + "\n\nEste enlace expira en 30 minutos.\n\n"
                    + "Si no solicitaste esto, ignora este mensaje.");
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace(); // 👈 añade esto temporalmente
            System.out.println("ERROR AL ENVIAR EMAIL: " + e.getMessage());
        }

        model.addAttribute("mensaje", "Si existe una cuenta con ese email, recibirás un enlace en breve.");
        return "views/forgot-password/forgot-password";
    }

    @GetMapping("/reset-password")
    public String resetPasswordForm(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "views/reset-password/reset-password";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam String token,
                                       @RequestParam String password,
                                       Model model) {
        try {
            resetService.resetPassword(token, password);
            return "redirect:/login?resetSuccess";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("token", token);
            return "views/reset-password/reset-password";
        }
    }
}