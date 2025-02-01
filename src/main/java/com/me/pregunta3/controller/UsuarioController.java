package com.me.pregunta3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.me.pregunta3.model.DTO.UsuarioDTO;
import com.me.pregunta3.model.DTO.services.interfaces.IUsuarioServicio;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioServicio usuarioServicio;

    // Listar todos los usuarios
    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        List<UsuarioDTO> lista = usuarioServicio.listarUsuarios();
        model.addAttribute("lista", lista);

        return "usuarios_listar";
    }

    // Listar usuario por id
    @GetMapping("/listar/{id}")
    public String listarUsuarioId(@PathVariable Long id, Model model) {
        UsuarioDTO usuario = usuarioServicio.listarUsuarioId(id);
        model.addAttribute("usuario", usuario);
        return "usuario_listar_id";
    }

    // Crear un nuevo usuario
    @GetMapping("/crear_form") // Metodo GET para mostrar la vista
    public String crearUsuarioForm(UsuarioDTO usuarioDTO) {
        return "usuario_crear";
    }

    @PostMapping("/crear") // Metodo POST para mandar la informacion
    public String crearUsuario(@ModelAttribute UsuarioDTO usuarioDTO, @RequestParam String password) {
        usuarioServicio.crearUsuario(usuarioDTO, password);

        return "redirect:/usuarios/listar";
    }

    // Actualizar un usuario
    @GetMapping("/actualizar_form/{id}")
    public String actualizarUsuarioForm(@PathVariable Long id, Model model) {
        UsuarioDTO usuarioDTO = usuarioServicio.listarUsuarioId(id);
        model.addAttribute("usuario", usuarioDTO);

        return "usuario_actualizar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute UsuarioDTO usuarioDTO) {
        usuarioServicio.actualizarUsuario(id, usuarioDTO);

        return "redirect:/usuarios/listar";
    }

    // Elimiar un usuario
    @GetMapping("/eliminar_form")
    public String eliminarUsuarioForm(Model model) {
        List<UsuarioDTO> lista = usuarioServicio.listarUsuarios();
        model.addAttribute("lista", lista);
        return "usuario_eliminar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioServicio.eliminarUsuario(id);

        return "redirect:/usuarios/listar";
    }

}
