package com.me.pregunta3.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.pregunta3.repository.UsuarioRepository;
import com.me.pregunta3.model.models.UsuarioModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Getter
@Setter
@Controller
@RequestMapping("/v1")
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/home")
    public String home() {
        return "home"; // Debe coincidir con home.html en /templates
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin(Model model, Principal principal) {
        //model.addAttribute("nombreAdmin", principal.getName());
        String nombreUsuario = principal.getName(); // Obtener el nombre del usuario
        UsuarioModel usuario = usuarioRepository.findByUsername(nombreUsuario); // Buscar el usuario en la base de datos
        model.addAttribute("usuario", usuario);
        return "dashboard_admin"; // Asegúrate de que exista dashboard_admin.html
    }

    @GetMapping("/usuario")
    @PreAuthorize("hasAuthority('USER')")
    public String usuario(Model model, Principal principal) {
        //model.addAttribute("nombreUsuario", principal.getName()); // Obtiene el nombre del usuario autenticado
        String nombreUsuario = principal.getName(); // Obtener el nombre del usuario
        UsuarioModel usuario = usuarioRepository.findByUsername(nombreUsuario); // Buscar el usuario en la base de datos
        model.addAttribute("usuario", usuario); // Pasar el usuario al modelo
        return "dashboard_usuario"; // Nombre del archivo HTML
    }

    @GetMapping("/prueba")
    public String mostrarMensaje(Model model) {
        model.addAttribute("mensaje", "¡Hola desde Thymeleaf!");
        return "prueba";  // nombre del archivo HTML (sin extensión)
    }

}