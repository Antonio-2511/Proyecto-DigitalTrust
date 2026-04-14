package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad User.
 *
 * Proporciona:
 * - Operaciones CRUD automáticas (JpaRepository)
 * - Consultas personalizadas optimizadas para evitar LazyInitializationException
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 🔐 MÉTODO CLAVE PARA SPRING SECURITY
     *
     * Carga:
     * - Usuario
     * - Plan
     * - Role
     *
     * usando JOIN FETCH para evitar problemas de carga LAZY
     * durante la autenticación.
     *
     * 💡 Este método sustituye al findByUsername normal.
     */
    @Query("""
        SELECT u FROM User u
        JOIN FETCH u.plan
        JOIN FETCH u.role
        WHERE u.username = :username
    """)
    Optional<User> findByUsername(@Param("username") String username);

    /**
     * Busca un usuario por su correo electrónico (campo 'gmail').
     */
    Optional<User> findByGmail(String gmail);

    /**
     * Comprueba si existe un usuario con el email indicado.
     */
    boolean existsByGmail(String gmail);

    /**
     * Comprueba si existe otro usuario con el mismo email excluyendo uno concreto.
     */
    boolean existsByGmailAndUsernameNot(String gmail, String username);

    /**
     * Comprueba si existe un usuario con ese username.
     */
    boolean existsByUsername(String username);
}