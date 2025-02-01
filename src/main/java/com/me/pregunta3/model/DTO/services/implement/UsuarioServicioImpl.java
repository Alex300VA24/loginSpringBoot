package com.me.pregunta3.model.DTO.services.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.pregunta3.model.DAO.interfaces.IUsuarioDAO;
import com.me.pregunta3.model.DTO.UsuarioDTO;
import com.me.pregunta3.model.DTO.services.interfaces.IUsuarioServicio;
import com.me.pregunta3.model.models.UsuarioModel;

@Service
public class UsuarioServicioImpl implements IUsuarioServicio {

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    public List<UsuarioDTO> listarUsuarios() {

        ModelMapper modelMapper = new ModelMapper();

        return this.usuarioDAO.listarUsuarios()
                .stream()
                .map(entidad -> modelMapper.map(entidad, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO listarUsuarioId(Long id) {

        Optional<UsuarioModel> usuarioModel = this.usuarioDAO.listarUsuarioId(id);

        if (usuarioModel.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            UsuarioModel actualUsuario = usuarioModel.get();
            return modelMapper.map(actualUsuario, UsuarioDTO.class);
        } else {
            return new UsuarioDTO();
        }
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO, String password) {

        try {
            ModelMapper modelMapper = new ModelMapper();
            UsuarioModel usuarioModel = modelMapper.map(usuarioDTO, UsuarioModel.class);
            usuarioModel.setPassword(password);
            this.usuarioDAO.crearUsuario(usuarioModel);
            return usuarioDTO;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al guardar el usuario");
        }
    }

    @Override
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {

        Optional<UsuarioModel> usuarioModel = this.usuarioDAO.listarUsuarioId(id);

        if (usuarioModel.isPresent()) {
            UsuarioModel actualUsuario = usuarioModel.get();
            actualUsuario.setUsername(usuarioDTO.getUsername());
            actualUsuario.setEmail(usuarioDTO.getEmail());

            this.usuarioDAO.actualizarUsuario(actualUsuario);

            ModelMapper modelMapper = new ModelMapper();

            return modelMapper.map(actualUsuario, UsuarioDTO.class);
        } else {
            throw new IllegalArgumentException("El usuario no existe");
        }

    }

    @Override
    public void eliminarUsuario(Long id) {

        Optional<UsuarioModel> usuarioModel = this.usuarioDAO.listarUsuarioId(id);

        if (usuarioModel.isPresent()) {
            UsuarioModel actualUsuario = usuarioModel.get();
            this.usuarioDAO.eliminarUsuario(actualUsuario);
        }
    }

}
