package com.me.pregunta3.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroDTO {

    private Long Id;
    private String nombre;
    private String autor;
    private boolean disponible;

}
