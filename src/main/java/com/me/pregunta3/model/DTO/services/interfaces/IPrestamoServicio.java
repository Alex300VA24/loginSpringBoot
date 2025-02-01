package com.me.pregunta3.model.DTO.services.interfaces;

import java.util.List;

import com.me.pregunta3.model.DTO.PrestamoDTO;

public interface IPrestamoServicio {

    List<PrestamoDTO> listarPrestamos();

    PrestamoDTO listarPrestamoId(Long id);

    PrestamoDTO crearPrestamo(PrestamoDTO prestamoDTO);

    PrestamoDTO actualizarPrestamo(Long id, PrestamoDTO prestamoDTO);

    void eliminarPrestamo(Long id);

}
