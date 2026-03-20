package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Entidad que representa una fuente confiable dentro del sistema.
 * <p>
 * Una fuente confiable corresponde a una entidad verificada (como un banco,
 * empresa o institución) que puede utilizarse para contrastar información
 * y detectar posibles fraudes o suplantaciones.
 * </p>
 *
 * <p>
 * Está asociada a una {@link Advertencia} y puede tener múltiples {@link Mensaje}
 * relacionados que hacen referencia a dicha fuente.
 * </p>
 */
@Entity
@Table(name = "Fuente_Confiable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuenteConfiable {

    /**
     * Identificador único de la fuente confiable.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Fuente")
    private Long idFuente;

    /**
     * Nombre de la entidad (empresa, banco, organismo, etc.).
     */
    @Column(name = "Nombre_entidad", nullable = false, length = 100)
    private String nombreEntidad;

    /**
     * Tipo de entidad (por ejemplo: banco, empresa, organismo público).
     */
    @Column(name = "Tipo", length = 45)
    private String tipo;

    /**
     * Número de teléfono oficial de la entidad.
     */
    @Column(name = "Telefono", length = 15)
    private String telefono;

    /**
     * Dirección de correo electrónico oficial de la entidad.
     */
    @Column(name = "Email", length = 100)
    private String email;

    /**
     * Dominio web oficial de la entidad.
     * <p>
     * Este campo es clave para detectar phishing mediante comparación de URLs.
     * </p>
     */
    @Column(name = "Dominio", length = 45)
    private String dominio;

    /**
     * Advertencia asociada a esta fuente.
     * <p>
     * Relación muchos a uno: varias fuentes pueden estar vinculadas
     * a una misma advertencia.
     * </p>
     */
    @ManyToOne
    @JoinColumn(
            name = "Advertencia_Id",
            referencedColumnName = "Id",
            nullable = false
    )
    private Advertencia advertencia;

    /**
     * Lista de mensajes relacionados con esta fuente confiable.
     * <p>
     * Relación uno a muchos: una fuente puede estar asociada a múltiples mensajes.
     * </p>
     */
    @OneToMany(mappedBy = "fuenteConfiable")
    private List<Mensaje> mensajes;
}