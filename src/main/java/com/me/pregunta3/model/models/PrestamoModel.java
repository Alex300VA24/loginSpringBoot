package com.me.pregunta3.model.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prestamos")
public class PrestamoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", nullable = false)
    private UsuarioModel idUsuario;

    @ManyToOne
    @JoinColumn(name = "idLibro", referencedColumnName = "Id", nullable = false)
    private LibroModel idLibro;

    @Column(nullable = false)
    private LocalDate fechaPrestamo;

    private LocalDate fechaDevolucion;

    private boolean estado;

    @PrePersist
    public void prePersist() {
        this.fechaPrestamo = LocalDate.now();
        this.estado = true;
    }

}
