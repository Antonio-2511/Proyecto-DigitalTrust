package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Configuración de internacionalización (i18n) de la aplicación.
 *
 * Esta clase define:
 * - El resolver de idioma basado en sesión.
 * - Un interceptor que permite cambiar el idioma dinámicamente
 *   mediante un parámetro en la URL.
 *
 * Implementa {@link WebMvcConfigurer} para registrar interceptores
 * personalizados en Spring MVC.
 */
@Configuration
public class LocaleConfig implements WebMvcConfigurer {

    /**
     * Define el bean {@link LocaleResolver} que gestiona el idioma actual del usuario.
     *
     * Se utiliza {@link SessionLocaleResolver} para almacenar el locale en la sesión HTTP.
     * El idioma por defecto se establece en español ("es").
     *
     * @return el resolvedor de locale configurado
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale("es"));
        return resolver;
    }

    /**
     * Define el interceptor encargado de detectar cambios de idioma en las peticiones.
     *
     * Este interceptor busca el parámetro "lang" en la URL para cambiar el idioma,
     * por ejemplo: ?lang=en o ?lang=es
     *
     * @return el interceptor de cambio de locale
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    /**
     * Registra el interceptor de cambio de idioma en el sistema de Spring MVC.
     *
     * Esto permite que cada petición HTTP pueda modificar el idioma activo
     * si incluye el parámetro configurado.
     *
     * @param registry registro de interceptores de Spring
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}