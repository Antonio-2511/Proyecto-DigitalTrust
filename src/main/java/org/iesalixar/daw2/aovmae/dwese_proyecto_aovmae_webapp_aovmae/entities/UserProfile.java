package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "`User`")
public class UserProfile {

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

    // ===============================
    // RELACIÓN CON PLAN (ManyToOne)
    // ===============================

    @ManyToOne
    @JoinColumn(
            name = "Plan_Nombre_plan",
            referencedColumnName = "Nombre_plan",
            nullable = false
    )
    private Plan plan;

    // ===============================
    // CONSTRUCTORES
    // ===============================

    public UserProfile() {
    }

    public UserProfile(String username, String contrasenia, Plan plan) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.plan = plan;
        this.fechaCreacion = LocalDateTime.now();
    }

    // ===============================
    // GETTERS Y SETTERS
    // ===============================

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getGmail() {
        return gmail;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}