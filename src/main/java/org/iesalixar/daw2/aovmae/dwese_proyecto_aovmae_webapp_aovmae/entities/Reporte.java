package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reporte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_reporte")
    private Long id;

    @Column(name = "Titulo", length = 100)
    private String titulo;

    @Column(name = "Descripcion", length = 255)
    private String descripcion;

    @Column(name = "Fecha_reporte")
    private LocalDateTime fechaReporte;

    @ManyToOne
    @JoinColumn(
            name = "users_username",
            referencedColumnName = "username",
            nullable = false
    )
    private User user;
}