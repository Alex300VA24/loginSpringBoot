package com.me.pregunta3.model.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoDTO {

    private Long id;
    private Long idUsuario;
    private Long idLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean estado;

    public boolean esValido() {
        return this.idUsuario != null && this.idLibro != null && this.fechaDevolucion != null;
    }

}
