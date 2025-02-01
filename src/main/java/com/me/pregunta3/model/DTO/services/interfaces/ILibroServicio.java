package com.me.pregunta3.model.DTO.services.interfaces;

import java.util.List;

import com.me.pregunta3.model.DTO.LibroDTO;

public interface ILibroServicio {

    List<LibroDTO> listarLibros();

    LibroDTO listarLibroId(Long id);

    LibroDTO crearLibro(LibroDTO libroDTO);

    LibroDTO actualizaLibro(Long id, LibroDTO libroDTO);

    void eliminarLibro(Long id);
}
