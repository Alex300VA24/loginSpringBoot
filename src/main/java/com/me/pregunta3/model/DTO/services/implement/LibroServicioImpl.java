package com.me.pregunta3.model.DTO.services.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.pregunta3.model.DAO.interfaces.ILibroDAO;
import com.me.pregunta3.model.DTO.LibroDTO;
import com.me.pregunta3.model.DTO.services.interfaces.ILibroServicio;
import com.me.pregunta3.model.models.LibroModel;

@Service
public class LibroServicioImpl implements ILibroServicio {

    @Autowired
    private ILibroDAO libroDAO;

    @Override
    public List<LibroDTO> listarLibros() {

        ModelMapper modelMapper = new ModelMapper();

        return this.libroDAO.listarLibros()
                .stream()
                .map(entidad -> modelMapper.map(entidad, LibroDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LibroDTO listarLibroId(Long id) {

        Optional<LibroModel> libroModel = this.libroDAO.listarLibroId(id);

        if (libroModel.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            LibroModel actualLibro = libroModel.get();
            return modelMapper.map(actualLibro, LibroDTO.class);
        } else {
            return new LibroDTO();
        }
    }

    @Override
    public LibroDTO crearLibro(LibroDTO libroDTO) {

        try {
            ModelMapper modelMapper = new ModelMapper();
            LibroModel libroModel = modelMapper.map(libroDTO, LibroModel.class);
            this.libroDAO.crearLibro(libroModel);
            return libroDTO;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al guardar el libro");
        }
    }

    @Override
    public LibroDTO actualizaLibro(Long id, LibroDTO libroDTO) {

        Optional<LibroModel> libroModel = this.libroDAO.listarLibroId(id);

        if (libroModel.isPresent()) {
            LibroModel actualLibro = libroModel.get();
            actualLibro.setNombre(libroDTO.getNombre());
            actualLibro.setAutor(libroDTO.getAutor());
            actualLibro.setDisponible(libroDTO.isDisponible());

            this.libroDAO.actualizarLibro(actualLibro);

            ModelMapper modelMapper = new ModelMapper();

            return modelMapper.map(actualLibro, LibroDTO.class);

        } else {
            throw new IllegalArgumentException("El libro no existe");
        }
    }

    @Override
    public void eliminarLibro(Long id) {

        Optional<LibroModel> libroModel = this.libroDAO.listarLibroId(id);

        if (libroModel.isPresent()) {
            LibroModel actualLibro = libroModel.get();
            this.libroDAO.eliminarLibro(actualLibro);
        }
    }

}
