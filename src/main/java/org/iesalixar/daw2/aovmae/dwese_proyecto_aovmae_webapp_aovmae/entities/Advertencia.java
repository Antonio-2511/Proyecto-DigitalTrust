package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "advertencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advertencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(name = "nivel_criticidad", nullable = false)
    private Integer nivelCriticidad;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio;

    @Column(name = "es_emergencia", nullable = false)
    private boolean esEmergencia;


    // Advertencia es enviada por un usuario (N:1)
    @ManyToOne
    @JoinColumn(name = "usuario", nullable = false)
    private User user;

    // Advertencia es generada por una fuente confiable (N:1)
    @ManyToOne
    @JoinColumn(name = "fuente_confiable_id", nullable = false)
    private FuenteConfiable fuenteConfiable;

    public void setMensaje(Mensaje mensaje) {
    }
}


