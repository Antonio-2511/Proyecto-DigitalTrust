package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "username", length = 45)
    private String username;

    @Column(name = "Contrasenia", length = 100)
    private String contrasenia;

    @Column(name = "Fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "Telefono", length = 15)
    private String telefono;

    @Column(name = "Gmail", length = 100)
    private String gmail;

    @ManyToOne
    @JoinColumn(
            name = "Plan_Nombre_plan",
            referencedColumnName = "Nombre_plan",
            nullable = false
    )
    private Plan plan;

    @OneToMany(mappedBy = "user")
    private List<Advertencia> advertencias;

    @OneToMany(mappedBy = "user")
    private List<Mensaje> mensajes;

    @OneToMany(mappedBy = "user")
    private List<Reporte> reportes;
}