package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.controllers;


import jakarta.validation.Valid;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteCreateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.dtos.ReporteUpdateDTO;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.exceptions.ResourceNotFoundException;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services.ReporteService;
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
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public String list(
            @PageableDefault(size = 10, sort = "fechaReporte", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {

        model.addAttribute("page", reporteService.list(pageable));
        return "views/reportes/reporte-list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("reporte", new ReporteCreateDTO());
        return "views/reportes/reporte-form";
    }

    @PostMapping("/insert")
    public String insert(
            @Valid @ModelAttribute("reporte") ReporteCreateDTO dto,
            BindingResult result) {

        if (result.hasErrors()) {
            return "views/reportes/reporte-form";
        }

        reporteService.create(dto);
        return "redirect:/reportes";
    }

    @GetMapping("/edit")
    public String showEdit(@RequestParam Long id,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        try {
            model.addAttribute("reporte", reporteService.getForEdit(id));
            return "views/reportes/reporte-form";

        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Reporte no encontrado");
            return "redirect:/reportes";
        }
    }

    @PostMapping("/update")
    public String update(
            @Valid @ModelAttribute("reporte") ReporteUpdateDTO dto,
            BindingResult result) {

        if (result.hasErrors()) {
            return "views/reportes/reporte-form";
        }

        reporteService.update(dto);
        return "redirect:/reportes";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        reporteService.delete(id);
        return "redirect:/reportes";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam Long id, Model model) {
        model.addAttribute("reporte", reporteService.getDetail(id));
        return "views/reportes/reporte-detail";
    }
}