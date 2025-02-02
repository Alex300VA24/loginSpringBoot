package com.me.pregunta3.model.DAO.interfaces;

import java.util.List;
import java.util.Optional;

import com.me.pregunta3.model.models.PrestamoModel;

public interface IPrestamoDAO {

    List<PrestamoModel> listarPrestamos();

    Optional<PrestamoModel> listarPrestamoId(Long id);

    void crearPrestamo(PrestamoModel prestamoModel);

    void actualizarPrestamo(PrestamoModel prestamoModel);

    void eliminarPrestamo(PrestamoModel prestamoModel);

    Optional<PrestamoModel> buscarPrestamoActivo(Long idLibro);

    List<PrestamoModel> prestamosByID(Long id);

}
