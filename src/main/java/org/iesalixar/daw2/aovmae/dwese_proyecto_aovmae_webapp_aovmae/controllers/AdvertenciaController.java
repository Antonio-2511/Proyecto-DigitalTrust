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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador MVC para la gestión de advertencias.
 *
 * Gestiona todas las operaciones CRUD relacionadas con advertencias:
 * - Listado paginado
 * - Creación
 * - Edición
 * - Eliminación
 * - Visualización de detalle
 *
 * Sigue el patrón Modelo-Vista-Controlador (MVC) de Spring.
 *
 * ⚠️ Seguridad:
 * - Usa validación con {@link Valid} para evitar entrada inválida.
 * - Delega la lógica en el servicio para evitar exposición directa del repositorio.
 */
@Controller
@RequestMapping("/advertencias")
public class AdvertenciaController {

    @Autowired
    private AdvertenciaService advertenciaService;

    @Autowired
    private FuenteConfiableRepository fuenteConfiableRepository;

    /**
     * Lista las advertencias paginadas.
     * - Si el usuario es MODERATOR o ADMIN ve todas las advertencias.
     * - Si es un usuario normal solo ve las suyas.
     *
     * 🔐 Autenticado
     */
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String list(
            @PageableDefault(size = 10, sort = "fechaEnvio", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model,
            Authentication authentication) {

        boolean esStaff = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_MODERATOR")
                        || a.getAuthority().equals("ROLE_ADMIN"));

        if (esStaff) {
            model.addAttribute("page", advertenciaService.list(pageable));
            model.addAttribute("esStaff", true);
        } else {
            model.addAttribute("advertencias", advertenciaService.listByUser(authentication.getName()));
            model.addAttribute("esStaff", false);
        }

        return "views/advertencias/advertencia-list";
    }

    /**
     * Muestra el formulario de creación de una nueva advertencia.
     *
     * 🔐 SOLO ADMIN
     */
    @GetMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String showNewForm(Model model) {
        model.addAttribute("advertencia", new AdvertenciaCreateDTO());
        model.addAttribute("fuentes", fuenteConfiableRepository.findAll());
        return "views/advertencias/advertencia-form";
    }

    /**
     * Procesa el formulario de creación de una nueva advertencia.
     * Si hay errores de validación vuelve al formulario.
     *
     * 🔐 SOLO ADMIN
     */
    @PostMapping("/insert")
    @PreAuthorize("hasRole('ADMIN')")
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

    /**
     * Muestra el formulario de edición de una advertencia existente.
     * Si no se encuentra la advertencia redirige al listado con mensaje de error.
     *
     * 🔐 SOLO ADMIN
     */
    @GetMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
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

    /**
     * Procesa el formulario de edición y actualiza la advertencia.
     * Si hay errores de validación vuelve al formulario.
     * Redirige con parámetro ?adv_updated para mostrar el toast de éxito.
     *
     * 🔐 SOLO ADMIN
     */
    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String update(
            @Valid @ModelAttribute("advertencia") AdvertenciaUpdateDTO dto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("fuentes", fuenteConfiableRepository.findAll());
            return "views/advertencias/advertencia-form";
        }

        advertenciaService.update(dto);
        return "redirect:/advertencias?adv_updated";  // parámetro recogido por el fragment de toasts
    }

    /**
     * Elimina una advertencia por su ID.
     * Redirige con parámetro ?adv_deleted para mostrar el toast de confirmación.
     *
     * 🔐 SOLO ADMIN
     */
    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestParam Long id) {
        advertenciaService.delete(id);
        return "redirect:/advertencias?adv_deleted";  // parámetro recogido por el fragment de toasts
    }

    /**
     * Muestra el detalle de una advertencia concreta.
     *
     * 🔐 Autenticado
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("advertencia", advertenciaService.getDetail(id));
        return "views/advertencias/advertencia-detail";
    }
}