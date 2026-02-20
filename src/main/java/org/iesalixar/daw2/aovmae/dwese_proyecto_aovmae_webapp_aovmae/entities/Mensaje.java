package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Mensaje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_mensaje")
    private Long idMensaje;

    @Column(name = "Contenido_texto", length = 255)
    private String contenidoTexto;

    @Column(name = "Origen", length = 45)
    private String origen;

    @Column(name = "Nivel_riesgo", length = 45)
    private String nivelRiesgo;

    @Column(name = "Resultado_analisis", length = 45)
    private String resultadoAnalisis;

    @Column(name = "fecha_analisis")
    private LocalDateTime fechaAnalisis;

    @ManyToOne
    @JoinColumn(
            name = "users_username",
            referencedColumnName = "username",
            nullable = false
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "Fuente_Confiable_Id_Fuente",
            referencedColumnName = "Id_Fuente",
            nullable = false
    )
    private FuenteConfiable fuenteConfiable;
}