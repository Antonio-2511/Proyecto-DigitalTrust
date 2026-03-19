package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private PasswordResetService resetService;

    @GetMapping("/login")
    public String login() {
        return "views/login/login";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordForm() {
        return "views/forgot-password/forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email) {

        String token = resetService.createToken(email);

        System.out.println("LINK: http://localhost:8080/reset-password?token=" + token);

        return "redirect:/login?resetSent";
    }

    @GetMapping("/reset-password")
    public String resetPasswordForm(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "views/reset-password/reset-password";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(
            @RequestParam String token,
            @RequestParam String password) {

        resetService.resetPassword(token, password);

        return "redirect:/login?resetSuccess";
    }
}