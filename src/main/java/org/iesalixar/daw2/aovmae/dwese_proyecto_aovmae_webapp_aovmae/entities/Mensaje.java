package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "mensaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {

    @Id
    @Column(name = "id_mensaje")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensaje;

    // Relación con usuario (muchos mensajes pertenecen a un usuario)
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @Column(name = "contenido_texto", nullable = false, columnDefinition = "TEXT")
    private String contenidoTexto;

    @Column(length = 50)
    private String origen;

    @Column(name = "nivel_riesgo", length = 20)
    private String nivelRiesgo;

    @Column(name = "resultado_analisis", length = 255)
    private String resultadoAnalisis;

    @Column(name = "fecha_analisis", nullable = false)
    private LocalDateTime fechaAnalisis;

    @ManyToOne
    @JoinColumn(name = "id_fuenteConfiable", nullable = false)
    private FuenteConfiable fuenteConfiable; // ⚠️ si ya es true por diseño
}
