package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Advertencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Advertencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Titulo", length = 100)
    private String titulo;

    @Column(name = "Nivel_Criticidad", length = 45)
    private String nivelCriticidad;

    @Column(name = "Descripcion", length = 255)
    private String descripcion;

    @Column(name = "Fecha_de_envio")
    private LocalDateTime fechaEnvio;

    @Column(name = "Es_emergencia")
    private Boolean esEmergencia;

    @ManyToOne
    @JoinColumn(
            name = "users_username",
            referencedColumnName = "username",
            nullable = false
    )
    private User user;

    @OneToMany(mappedBy = "advertencia")
    private List<FuenteConfiable> fuentes;
}