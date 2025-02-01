package com.me.pregunta3.model.DAO.interfaces;

import java.util.List;
import java.util.Optional;

import com.me.pregunta3.model.models.LibroModel;

public interface ILibroDAO {

    List<LibroModel> listarLibros();

    Optional<LibroModel> listarLibroId(Long id);

    void crearLibro(LibroModel libroModel);

    void actualizarLibro(LibroModel libroModel);

    void eliminarLibro(LibroModel libroModel);

}
