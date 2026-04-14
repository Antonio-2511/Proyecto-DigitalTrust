package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;

import jakarta.validation.Valid;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.UserUpdateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.exceptions.DuplicateResourceException;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.exceptions.ResourceNotFoundException;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.PlanRepository;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

/**
 * Controlador MVC para la gestión de usuarios.
 *
 * Gestiona operaciones CRUD completas:
 * - Listado paginado de usuarios
 * - Creación
 * - Edición
 * - Eliminación
 * - Visualización de detalle
 *
 * Integra:
 * - Validación (@Valid)
 * - Internacionalización (MessageSource)
 * - Manejo de excepciones controladas
 *
 * ⚠️ Seguridad (CRÍTICO):
 * Este controlador gestiona identidades de usuario, por lo que debe:
 * - Validar estrictamente los datos de entrada
 * - Evitar duplicados (usuarios/emails)
 * - Proteger endpoints con autenticación/autorización
 */
@Controller
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private MessageSource messageSource;

    /**
     * 🔐 SOLO ADMIN
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String list(
            @PageableDefault(size = 10, sort = "gmail", direction = Sort.Direction.ASC)
            Pageable pageable,
            Model model) {

        Page<UserDTO> page = userService.list(pageable);
        model.addAttribute("page", page);

        return "views/usuarios/usuario-list";
    }

    /**
     * 🔓 Registro abierto
     */
    @GetMapping("/new")
    public String showNewForm(Model model) {

        model.addAttribute("usuario", new UserCreateDTO());
        model.addAttribute("planes", planRepository.findAll());
        model.addAttribute("modo", "crear");

        return "views/usuarios/usuario-form";
    }

    /**
     * 🔓 Registro abierto
     */
    @PostMapping("/insert")
    public String insert(
            @Valid @ModelAttribute("usuario") UserCreateDTO dto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model,
            Locale locale) {

        if (result.hasErrors()) {
            model.addAttribute("planes", planRepository.findAll());
            model.addAttribute("modo", "crear");
            return "views/usuarios/usuario-form";
        }

        try {

            userService.create(dto);

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    messageSource.getMessage("msg.usuario.created", null, locale)
            );

            return "redirect:/login";

        } catch (DuplicateResourceException ex) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    messageSource.getMessage(
                            "msg.usuario.duplicate",
                            null,
                            "El usuario o email ya existe",
                            locale
                    )
            );

            return "redirect:/usuarios/new";
        }
    }

    /**
     * 🔐 Usuario o ADMIN
     */
    @GetMapping("/edit")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public String showEdit(@RequestParam String username,
                           Model model,
                           RedirectAttributes redirectAttributes,
                           Locale locale) {

        try {

            model.addAttribute("usuario", userService.getForEdit(username));
            model.addAttribute("planes", planRepository.findAll());
            model.addAttribute("modo", "editar");

            return "views/usuarios/usuario-form";

        } catch (ResourceNotFoundException ex) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    messageSource.getMessage(
                            "msg.usuario.notfound",
                            null,
                            "Usuario no encontrado",
                            locale
                    )
            );

            return "redirect:/usuarios";
        }
    }

    /**
     * 🔐 Usuario o ADMIN
     */
    @PostMapping("/update")
    @PreAuthorize("#dto.username == authentication.name or hasRole('ADMIN')")
    public String update(
            @Valid @ModelAttribute("usuario") UserUpdateDTO dto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model,
            Locale locale) {

        if (result.hasErrors()) {
            model.addAttribute("planes", planRepository.findAll());
            model.addAttribute("modo", "editar");
            return "views/usuarios/usuario-form";
        }

        try {

            userService.update(dto);

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    messageSource.getMessage("msg.usuario.updated", null, locale)
            );

            return "redirect:/usuarios";

        } catch (DuplicateResourceException ex) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    messageSource.getMessage(
                            "msg.usuario.duplicate",
                            null,
                            "El usuario o email ya existe",
                            locale
                    )
            );

            return "redirect:/usuarios/edit?username=" + dto.getUsername();
        }
    }

    /**
     * 🔐 SOLO ADMIN
     */
    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestParam String username,
                         RedirectAttributes redirectAttributes,
                         Locale locale) {

        try {

            userService.delete(username);

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    messageSource.getMessage("msg.usuario.deleted", null, locale)
            );

            return "redirect:/usuarios";

        } catch (ResourceNotFoundException ex) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    messageSource.getMessage(
                            "msg.usuario.notfound",
                            null,
                            "Usuario no encontrado",
                            locale
                    )
            );

            return "redirect:/usuarios";
        }
    }

    /**
     * 🔐 Usuario o ADMIN
     */
    @GetMapping("/detail")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public String detail(@RequestParam String username,
                         Model model,
                         RedirectAttributes redirectAttributes,
                         Locale locale) {

        try {

            model.addAttribute("usuario", userService.getDetail(username));
            return "views/usuarios/usuario-detail";

        } catch (ResourceNotFoundException ex) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    messageSource.getMessage(
                            "msg.usuario.notfound",
                            null,
                            "Usuario no encontrado",
                            locale
                    )
            );

            return "redirect:/usuarios";
        }
    }
}