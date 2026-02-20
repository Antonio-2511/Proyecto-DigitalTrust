package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "FuenteConfiable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuenteConfiable {

    @Id
    @Column(name = "id_fuente")
    private Long idFuente;

    @Column(name = "nombre_entidad", nullable = false, length = 150)
    private String nombreEntidad;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(length = 15)
    private String telefono;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 150)
    private String dominio;

    @OneToMany(mappedBy = "fuenteConfiable")
    private List<Advertencia> advertencias;

    // Usuario envía muchos mensajes
    @OneToMany(mappedBy = "fuenteConfiable")
    private List<Mensaje> mensajes;
}
