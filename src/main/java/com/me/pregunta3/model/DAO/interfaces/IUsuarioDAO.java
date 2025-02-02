package com.me.pregunta3.model.DAO.interfaces;

import java.util.List;
import java.util.Optional;

import com.me.pregunta3.model.models.UsuarioModel;

public interface IUsuarioDAO {

    List<UsuarioModel> listarUsuarios();

    Optional<UsuarioModel> listarUsuarioId(Long id);

    void crearUsuario(UsuarioModel usuarioModel);

    void actualizarUsuario(UsuarioModel usuarioModel);

    void eliminarUsuario(UsuarioModel usuarioModel);

    Long encontrarNombreById(String nombre);
}
