package com.me.pregunta3.model.DTO.services.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.pregunta3.model.DAO.interfaces.ILibroDAO;
import com.me.pregunta3.model.DAO.interfaces.IPrestamoDAO;
import com.me.pregunta3.model.DAO.interfaces.IUsuarioDAO;
import com.me.pregunta3.model.DTO.PrestamoDTO;
import com.me.pregunta3.model.DTO.services.interfaces.IPrestamoServicio;
import com.me.pregunta3.model.models.LibroModel;
import com.me.pregunta3.model.models.PrestamoModel;
import com.me.pregunta3.model.models.UsuarioModel;

@Service
public class PrestamoServicioImpl implements IPrestamoServicio {

    @Autowired
    private IPrestamoDAO prestamoDAO;

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Autowired
    private ILibroDAO libroDAO;

    @Override
    public List<PrestamoDTO> listarPrestamos() {

        ModelMapper modelMapper = new ModelMapper();

        return this.prestamoDAO.listarPrestamos()
                .stream()
                .map(entidad -> modelMapper.map(entidad, PrestamoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PrestamoDTO listarPrestamoId(Long id) {

        Optional<PrestamoModel> prestamoModel = this.prestamoDAO.listarPrestamoId(id);

        if (prestamoModel.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            PrestamoModel actualPrestamo = prestamoModel.get();
            return modelMapper.map(actualPrestamo, PrestamoDTO.class);
        } else {
            return new PrestamoDTO();
        }
    }

    @Override
    public PrestamoDTO crearPrestamo(PrestamoDTO prestamoDTO) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            PrestamoModel prestamoModel = modelMapper.map(prestamoDTO, PrestamoModel.class);

            // Verifica si el usuario existe
            Optional<UsuarioModel> usuarioOpt = this.usuarioDAO.listarUsuarioId(prestamoDTO.getIdUsuario());
            if (!usuarioOpt.isPresent()) {
                throw new IllegalArgumentException("El usuario con ID " + prestamoDTO.getIdUsuario() + " no existe.");
            }
            prestamoModel.setIdUsuario(usuarioOpt.get());

            // Verifica si el libro existe
            Optional<LibroModel> libroOpt = this.libroDAO.listarLibroId(prestamoDTO.getIdLibro());
            if (!libroOpt.isPresent()) {
                throw new IllegalArgumentException("El libro con ID " + prestamoDTO.getIdLibro() + " no existe.");
            }
            prestamoModel.setIdLibro(libroOpt.get());

            // Verificar si el libro está disponible
            if (!libroOpt.isPresent() || !libroOpt.get().isDisponible()) {
                throw new IllegalArgumentException("El libro no está disponible para préstamo.");
            }

            // Verificar si el préstamo está activo (si ya se ha prestado el libro a otro
            // usuario)
            Optional<PrestamoModel> prestamoActiva = prestamoDAO.buscarPrestamoActivo(prestamoDTO.getIdLibro());
            if (prestamoActiva.isPresent()) {
                throw new IllegalArgumentException("Este libro ya está prestado.");
            }

            this.prestamoDAO.crearPrestamo(prestamoModel);
            return prestamoDTO;
        } catch (IllegalArgumentException e) {
            // Log de error específico
            e.printStackTrace(); // Imprime la traza para más detalles
            throw e; // Vuelve a lanzar la excepción para que se propague
        } catch (Exception e) {
            // Maneja otras excepciones generales y muestra detalles del error
            e.printStackTrace(); // Imprime la traza para más detalles
            throw new RuntimeException("Error al guardar el préstamo: " + e.getMessage(), e);
        }
    }

    @Override
    public PrestamoDTO actualizarPrestamo(Long id, PrestamoDTO prestamoDTO) {

        Optional<PrestamoModel> prestamoModel = this.prestamoDAO.listarPrestamoId(id);

        if (prestamoModel.isPresent()) {
            PrestamoModel actualPrestamo = prestamoModel.get();

            actualPrestamo.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
            actualPrestamo.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
            actualPrestamo.setEstado(prestamoDTO.isEstado());

            this.prestamoDAO.actualizarPrestamo(actualPrestamo);

            ModelMapper modelMapper = new ModelMapper();

            return modelMapper.map(actualPrestamo, PrestamoDTO.class);

        } else {
            throw new IllegalArgumentException("El prestamo no existe");
        }
    }

    @Override
    public void eliminarPrestamo(Long id) {

        Optional<PrestamoModel> prestamoModel = this.prestamoDAO.listarPrestamoId(id);

        if (prestamoModel.isPresent()) {
            PrestamoModel actualPrestamo = prestamoModel.get();
            this.prestamoDAO.eliminarPrestamo(actualPrestamo);
        }
    }

}
