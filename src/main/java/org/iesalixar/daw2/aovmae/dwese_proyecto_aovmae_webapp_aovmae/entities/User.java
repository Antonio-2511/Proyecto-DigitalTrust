package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, length = 50, unique = true)
    private String nombreUsuario;

    @Column(nullable = false)
    private String contrasena;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(length = 15)
    private String telefono;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 25)
    private String rol;


    @OneToMany(mappedBy = "usuario")
    private List<Advertencia> advertencias;

    // Usuario envía muchos mensajes
    @OneToMany(mappedBy = "usuario")
    private List<Mensaje> mensajes;

    // Usuario realiza muchos reportes
    @OneToMany(mappedBy = "usuario")
    private List<Reporte> reportes;

    // Usuario contrata un plan (N:1)
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;
}

