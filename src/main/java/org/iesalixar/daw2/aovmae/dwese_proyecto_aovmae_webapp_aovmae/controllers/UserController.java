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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String list(
            @PageableDefault(size = 10, sort = "email", direction = Sort.Direction.ASC)
            Pageable pageable,
            Model model) {

        Page<UserDTO> page = userService.list(pageable);
        model.addAttribute("page", page);
        return "views/usuarios/usuario-list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("usuario", new UserCreateDTO());
        model.addAttribute("planes", planRepository.findAll());
        return "views/usuarios/usuario-form";
    }

    @PostMapping("/insert")
    public String insert(
            @Valid @ModelAttribute("usuario") UserCreateDTO dto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model,
            Locale locale) {

        if (result.hasErrors()) {
            model.addAttribute("planes", planRepository.findAll());
            return "views/usuarios/usuario-form";
        }

        try {
            userService.create(dto);
            return "redirect:/usuarios";

        } catch (DuplicateResourceException ex) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    messageSource.getMessage("msg.usuario.duplicate", null, locale)
            );
            return "redirect:/usuarios/new";
        }
    }

    @GetMapping("/edit")
    public String showEdit(@RequestParam Long id,
                           Model model,
                           RedirectAttributes redirectAttributes,
                           Locale locale) {

        try {
            model.addAttribute("usuario", userService.getForEdit(id));
            model.addAttribute("planes", planRepository.findAll());
            return "views/usuarios/usuario-form";

        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    messageSource.getMessage("msg.usuario.notfound", null, locale)
            );
            return "redirect:/usuarios";
        }
    }

    @PostMapping("/update")
    public String update(
            @Valid @ModelAttribute("usuario") UserUpdateDTO dto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model,
            Locale locale) {

        if (result.hasErrors()) {
            model.addAttribute("planes", planRepository.findAll());
            return "views/usuarios/usuario-form";
        }

        try {
            userService.update(dto);
            return "redirect:/usuarios";

        } catch (DuplicateResourceException ex) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    messageSource.getMessage("msg.usuario.duplicate", null, locale)
            );
            return "redirect:/usuarios/edit?id=" + dto.getId();
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id,
                         RedirectAttributes redirectAttributes,
                         Locale locale) {

        try {
            userService.delete(id);
            return "redirect:/usuarios";

        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    messageSource.getMessage("msg.usuario.notfound", null, locale)
            );
            return "redirect:/usuarios";
        }
    }

    @GetMapping("/detail")
    public String detail(@RequestParam Long id,
                         Model model,
                         RedirectAttributes redirectAttributes,
                         Locale locale) {

        try {
            model.addAttribute("usuario", userService.getDetail(id));
            return "views/usuarios/usuario-detail";

        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    messageSource.getMessage("msg.usuario.notfound", null, locale)
            );
            return "redirect:/usuarios";
        }
    }
}
