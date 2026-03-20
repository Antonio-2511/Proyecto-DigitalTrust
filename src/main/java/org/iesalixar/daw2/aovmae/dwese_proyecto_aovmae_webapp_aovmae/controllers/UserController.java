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

    /**
     * Servicio que contiene la lógica de negocio de usuarios.
     */
    @Autowired
    private UserService userService;

    /**
     * Repositorio de planes/membresías.
     *
     * Se utiliza para asignar planes a los usuarios.
     */
    @Autowired
    private PlanRepository planRepository;

    /**
     * Fuente de mensajes para internacionalización (i18n).
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Muestra el listado paginado de usuarios.
     *
     * @param pageable configuración de paginación (orden por gmail ASC)
     * @param model modelo de la vista
     * @return vista de listado
     */
    @GetMapping
    public String list(
            @PageableDefault(size = 10, sort = "gmail", direction = Sort.Direction.ASC)
            Pageable pageable,
            Model model) {

        Page<UserDTO> page = userService.list(pageable);
        model.addAttribute("page", page);

        return "views/usuarios/usuario-list";
    }

    /**
     * Muestra el formulario de creación de usuario.
     *
     * @param model modelo de la vista
     * @return vista del formulario en modo "crear"
     */
    @GetMapping("/new")
    public String showNewForm(Model model) {

        model.addAttribute("usuario", new UserCreateDTO());
        model.addAttribute("planes", planRepository.findAll());
        model.addAttribute("modo", "crear");

        return "views/usuarios/usuario-form";
    }

    /**
     * Procesa la creación de un nuevo usuario.
     *
     * @param dto datos del usuario
     * @param result resultado de validación
     * @param redirectAttributes atributos flash
     * @param model modelo de la vista
     * @param locale idioma actual
     * @return redirección o formulario en caso de error
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

            return "redirect:/usuarios";

        } catch (DuplicateResourceException ex) {

            /**
             * Manejo de duplicados (username/email).
             */
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
     * Muestra el formulario de edición de usuario.
     *
     * @param username identificador del usuario
     * @param model modelo de la vista
     * @param redirectAttributes atributos flash
     * @param locale idioma
     * @return vista de edición o redirección si no existe
     */
    @GetMapping("/edit")
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
     * Procesa la actualización de un usuario.
     *
     * @param dto datos actualizados
     * @param result validación
     * @param redirectAttributes atributos flash
     * @param model modelo
     * @param locale idioma
     * @return redirección o formulario si hay errores
     */
    @PostMapping("/update")
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
     * Elimina un usuario.
     *
     * @param username identificador del usuario
     * @param redirectAttributes atributos flash
     * @param locale idioma
     * @return redirección al listado
     *
     * ⚠️ Seguridad:
     * - Validar permisos (solo admins)
     * - Protección CSRF
     */
    @PostMapping("/delete")
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
     * Muestra el detalle de un usuario.
     *
     * @param username identificador
     * @param model modelo de la vista
     * @param redirectAttributes atributos flash
     * @param locale idioma
     * @return vista de detalle o redirección
     */
    @GetMapping("/detail")
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