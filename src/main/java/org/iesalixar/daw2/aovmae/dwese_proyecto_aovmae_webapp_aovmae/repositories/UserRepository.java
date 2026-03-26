package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad User.
 * * Al extender de JpaRepository, Spring proporciona métodos básicos (save, delete, etc.)
 * y permite definir consultas personalizadas mediante nombres de métodos.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Busca un usuario por nombre de usuario.
     * El EntityGraph fuerza la carga del Plan (FetchType.EAGER) para evitar
     * el error LazyInitializationException al autenticar.
     * * @param username Nombre de usuario (Clave primaria)
     * @return Un Optional que contiene el usuario si existe
     */
    @EntityGraph(attributePaths = "plan")
    Optional<User> findByUsername(String username);

    /**
     * Busca un usuario por su correo electrónico (campo 'gmail' en tu BD).
     * * @param gmail Correo electrónico
     * @return Un Optional con el usuario
     */
    Optional<User> findByGmail(String gmail);

    /**
     * Comprueba si existe un usuario con el email indicado.
     */
    boolean existsByGmail(String gmail);

    /**
     * Comprueba si existe otro usuario con el mismo email excluyendo un id.
     * Útil para validaciones en formularios de edición de perfil.
     */
    boolean existsByGmailAndUsernameNot(String gmail, String username);

    /**
     * Comprueba si existe un usuario con el nombre de usuario indicado.
     */
    boolean existsByUsername(String username);
}