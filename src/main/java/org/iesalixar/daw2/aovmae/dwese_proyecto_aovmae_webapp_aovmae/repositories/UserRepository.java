package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por nombre de usuario.
     * Usado por Spring Security para autenticación.
     */
    @EntityGraph(attributePaths = "plan")
    Optional<User> findByUsername(String username);

    /**
     * Busca un usuario por email.
     */
    Optional<User> findByGmail(String gmail);

    /**
     * Comprueba si existe un usuario con el email indicado.
     */
    boolean existsByGmail(String gmail);

    /**
     * Comprueba si existe otro usuario con el mismo email excluyendo un id.
     * Usado en edición.
     */
    boolean existsByGmailAndUsernameNot(String gmail, String username);

    /**
     * Comprueba si existe un usuario con el nombre de usuario indicado.
     */
    boolean existsByUsername(String username);
}
