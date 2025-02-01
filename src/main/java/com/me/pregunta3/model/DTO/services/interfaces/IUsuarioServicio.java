package com.me.pregunta3.model.DTO.services.interfaces;

import java.util.List;

import com.me.pregunta3.model.DTO.UsuarioDTO;

public interface IUsuarioServicio {

    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO listarUsuarioId(Long id);

    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO, String password);

    UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO);

    void eliminarUsuario(Long id);
}
