package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Fuente_Confiable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuenteConfiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Fuente")
    private Long idFuente;

    @Column(name = "Nombre_entidad", length = 100)
    private String nombreEntidad;

    @Column(name = "Tipo", length = 45)
    private String tipo;

    @Column(name = "Telefono", length = 15)
    private String telefono;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "Dominio", length = 45)
    private String dominio;

    @ManyToOne
    @JoinColumn(
            name = "Advertencia_Id",
            referencedColumnName = "Id",
            nullable = false
    )
    private Advertencia advertencia;

    @OneToMany(mappedBy = "fuenteConfiable")
    private List<Mensaje> mensajes;
}