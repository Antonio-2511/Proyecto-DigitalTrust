package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;


import jakarta.validation.Valid;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.AdvertenciaUpdateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.exceptions.ResourceNotFoundException;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.FuenteConfiableRepository;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.AdvertenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/advertencias")
public class AdvertenciaController {

    @Autowired
    private AdvertenciaService advertenciaService;

    @Autowired
    private FuenteConfiableRepository fuenteConfiableRepository;

    @GetMapping
    public String list(
            @PageableDefault(size = 10, sort = "fechaEnvio", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {

        model.addAttribute("page", advertenciaService.list(pageable));
        return "views/advertencias/advertencia-list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("advertencia", new AdvertenciaCreateDTO());
        model.addAttribute("fuentes", fuenteConfiableRepository.findAll());
        return "views/advertencias/advertencia-form";
    }

    @PostMapping("/insert")
    public String insert(
            @Valid @ModelAttribute("advertencia") AdvertenciaCreateDTO dto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("fuentes", fuenteConfiableRepository.findAll());
            return "views/advertencias/advertencia-form";
        }

        advertenciaService.create(dto);
        return "redirect:/advertencias";
    }

    @GetMapping("/edit")
    public String showEdit(@RequestParam Long id,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        try {
            model.addAttribute("advertencia", advertenciaService.getForEdit(id));
            model.addAttribute("fuentes", fuenteConfiableRepository.findAll());
            return "views/advertencias/advertencia-form";

        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Advertencia no encontrada");
            return "redirect:/advertencias";
        }
    }

    @PostMapping("/update")
    public String update(
            @Valid @ModelAttribute("advertencia") AdvertenciaUpdateDTO dto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("fuentes", fuenteConfiableRepository.findAll());
            return "views/advertencias/advertencia-form";
        }

        advertenciaService.update(dto);
        return "redirect:/advertencias";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        advertenciaService.delete(id);
        return "redirect:/advertencias";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam Long id, Model model) {
        model.addAttribute("advertencia", advertenciaService.getDetail(id));
        return "views/advertencias/advertencia-detail";
    }
}
