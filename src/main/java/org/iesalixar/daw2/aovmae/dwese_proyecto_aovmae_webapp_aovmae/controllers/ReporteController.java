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

/**
 * Controlador MVC para la gestión de reportes de posibles estafas.
 *
 * Gestiona operaciones CRUD:
 * - Listado de reportes
 * - Creación de nuevos reportes
 * - Edición
 * - Eliminación
 * - Visualización de detalle
 *
 * ⚠️ Contexto de ciberseguridad:
 * Los reportes pueden contener información sensible o denuncias de fraude,
 * por lo que deben tratarse con especial cuidado (validación, control de acceso, etc.).
 */
@Controller
@RequestMapping("/reportes")
public class ReporteController {

    /**
     * Servicio que contiene la lógica de negocio de reportes.
     */
    @Autowired
    private ReporteService reporteService;

    /**
     * Muestra el listado paginado de reportes.
     *
     * @param pageable configuración de paginación (por defecto: 10 elementos, orden por fechaReporte DESC)
     * @param model modelo de la vista
     * @return vista de listado de reportes
     */
    @GetMapping
    public String list(
            @PageableDefault(size = 10, sort = "fechaReporte", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {

        model.addAttribute("page", reporteService.list(pageable));
        return "views/reportes/reporte-list";
    }

    /**
     * Muestra el formulario para crear un nuevo reporte.
     *
     * @param model modelo de la vista
     * @return vista del formulario
     */
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("reporte", new ReporteCreateDTO());
        return "views/reportes/reporte-form";
    }

    /**
     * Procesa la creación de un nuevo reporte.
     *
     * @param dto datos del reporte
     * @param result resultado de la validación
     * @return redirección al listado o formulario en caso de error
     */
    @PostMapping("/insert")
    public String insert(
            @Valid @ModelAttribute("reporte") ReporteCreateDTO dto,
            BindingResult result) {

        /**
         * Validación de entrada para evitar datos incorrectos o maliciosos.
         */
        if (result.hasErrors()) {
            return "views/reportes/reporte-form";
        }

        reporteService.create(dto);
        return "redirect:/reportes";
    }

    /**
     * Muestra el formulario de edición de un reporte existente.
     *
     * @param id identificador del reporte
     * @param model modelo de la vista
     * @param redirectAttributes atributos para redirección
     * @return vista de edición o redirección si no existe
     */
    @GetMapping("/edit")
    public String showEdit(@RequestParam Long id,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        try {
            model.addAttribute("reporte", reporteService.getForEdit(id));
            return "views/reportes/reporte-form";

        } catch (ResourceNotFoundException ex) {
            /**
             * Manejo de error controlado si el recurso no existe.
             */
            redirectAttributes.addFlashAttribute("errorMessage", "Reporte no encontrado");
            return "redirect:/reportes";
        }
    }

    /**
     * Procesa la actualización de un reporte.
     *
     * @param dto datos actualizados
     * @param result resultado de la validación
     * @return redirección o formulario en caso de error
     */
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

    /**
     * Elimina un reporte por su ID.
     *
     * @param id identificador del reporte
     * @return redirección al listado
     *
     * ⚠️ Seguridad:
     * - Proteger contra CSRF
     * - Validar permisos del usuario (quién puede eliminar)
     */
    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        reporteService.delete(id);
        return "redirect:/reportes";
    }

    /**
     * Muestra el detalle de un reporte.
     *
     * @param id identificador del reporte
     * @param model modelo de la vista
     * @return vista de detalle
     */
    @GetMapping("/detail")
    public String detail(@RequestParam Long id, Model model) {
        model.addAttribute("reporte", reporteService.getDetail(id));
        return "views/reportes/reporte-detail";
    }
}