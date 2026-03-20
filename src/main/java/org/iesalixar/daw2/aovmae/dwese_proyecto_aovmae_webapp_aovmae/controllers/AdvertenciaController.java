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

    /**
     * Servicio que contiene la lógica de negocio de advertencias.
     */
    @Autowired
    private AdvertenciaService advertenciaService;

    /**
     * Repositorio para acceder a fuentes confiables.
     *
     * Se utiliza para poblar formularios (selects).
     */
    @Autowired
    private FuenteConfiableRepository fuenteConfiableRepository;

    /**
     * Muestra el listado paginado de advertencias.
     *
     * @param pageable configuración de paginación y ordenación (por defecto: fechaEnvio DESC)
     * @param model modelo de la vista
     * @return vista de listado de advertencias
     */
    @GetMapping
    public String list(
            @PageableDefault(size = 10, sort = "fechaEnvio", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {

        model.addAttribute("page", advertenciaService.list(pageable));
        return "views/advertencias/advertencia-list";
    }

    /**
     * Muestra el formulario de creación de una nueva advertencia.
     *
     * @param model modelo de la vista
     * @return vista del formulario
     */
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("advertencia", new AdvertenciaCreateDTO());
        model.addAttribute("fuentes", fuenteConfiableRepository.findAll());
        return "views/advertencias/advertencia-form";
    }

    /**
     * Procesa la creación de una nueva advertencia.
     *
     * @param dto datos de la advertencia a crear
     * @param result resultado de la validación
     * @param model modelo de la vista
     * @return redirección al listado o formulario en caso de error
     */
    @PostMapping("/insert")
    public String insert(
            @Valid @ModelAttribute("advertencia") AdvertenciaCreateDTO dto,
            BindingResult result,
            Model model) {

        /**
         * Validación de datos de entrada.
         *
         * ⚠️ Importante para prevenir:
         * - Datos maliciosos
         * - Inconsistencias
         */
        if (result.hasErrors()) {
            model.addAttribute("fuentes", fuenteConfiableRepository.findAll());
            return "views/advertencias/advertencia-form";
        }

        advertenciaService.create(dto);
        return "redirect:/advertencias";
    }

    /**
     * Muestra el formulario de edición de una advertencia existente.
     *
     * @param id identificador de la advertencia
     * @param model modelo de la vista
     * @param redirectAttributes atributos para redirección
     * @return vista del formulario o redirección si no existe
     */
    @GetMapping("/edit")
    public String showEdit(@RequestParam Long id,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        try {
            model.addAttribute("advertencia", advertenciaService.getForEdit(id));
            model.addAttribute("fuentes", fuenteConfiableRepository.findAll());
            return "views/advertencias/advertencia-form";

        } catch (ResourceNotFoundException ex) {
            /**
             * Manejo de error cuando el recurso no existe.
             *
             * ⚠️ Evita exponer información interna al usuario.
             */
            redirectAttributes.addFlashAttribute("errorMessage", "Advertencia no encontrada");
            return "redirect:/advertencias";
        }
    }

    /**
     * Procesa la actualización de una advertencia.
     *
     * @param dto datos actualizados
     * @param result resultado de la validación
     * @param model modelo de la vista
     * @return redirección al listado o formulario si hay errores
     */
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

    /**
     * Elimina una advertencia por su ID.
     *
     * @param id identificador de la advertencia
     * @return redirección al listado
     *
     * ⚠️ Seguridad:
     * - Este endpoint debería protegerse contra CSRF.
     * - Considerar confirmación previa en frontend.
     */
    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        advertenciaService.delete(id);
        return "redirect:/advertencias";
    }

    /**
     * Muestra el detalle de una advertencia.
     *
     * @param id identificador de la advertencia
     * @param model modelo de la vista
     * @return vista de detalle
     */
    @GetMapping("/detail")
    public String detail(@RequestParam Long id, Model model) {
        model.addAttribute("advertencia", advertenciaService.getDetail(id));
        return "views/advertencias/advertencia-detail";
    }
}