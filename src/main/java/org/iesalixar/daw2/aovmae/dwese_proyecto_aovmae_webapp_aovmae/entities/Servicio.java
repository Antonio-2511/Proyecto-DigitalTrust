package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Servicio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_servicio")
    private Integer id;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "Precio", nullable = false)
    private Double precio;

    @Column(name = "Imagen_url")
    private String imagenUrl;

    @Column(name = "Categoria")
    private String categoria;
}