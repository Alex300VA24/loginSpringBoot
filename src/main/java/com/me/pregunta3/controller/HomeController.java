package com.me.pregunta3.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.pregunta3.model.DAO.interfaces.IPrestamoDAO;
import com.me.pregunta3.model.DAO.interfaces.IUsuarioDAO;
import com.me.pregunta3.model.DTO.PrestamoDTO;
import com.me.pregunta3.model.DTO.services.interfaces.IPrestamoServicio;
import com.me.pregunta3.model.models.PrestamoModel;
import com.me.pregunta3.model.models.UsuarioModel;
import com.me.pregunta3.repository.UsuarioRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Controller
@RequestMapping("/v1")
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IPrestamoServicio prestamoServicio;

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Autowired
    private IPrestamoDAO prestamoDAO;

    @GetMapping("/home")
    public String home() {
        return "home"; // Debe coincidir con home.html en /templates
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin(Model model, Principal principal) {
        // model.addAttribute("nombreAdmin", principal.getName());
        String nombreUsuario = principal.getName(); // Obtener el nombre del usuario
        UsuarioModel usuario = usuarioRepository.findByUsername(nombreUsuario); // Buscar el usuario en la base de datos
        model.addAttribute("usuario", usuario);
        model.addAttribute("prestamoDTO", new PrestamoDTO());

        List<PrestamoDTO> lista = prestamoServicio.listarPrestamos();
        model.addAttribute("lista", lista);

        return "dashboard_admin"; // Asegúrate de que exista dashboard_admin.html
    }

    @GetMapping("/usuario")
    @PreAuthorize("hasAuthority('USER')")
    public String usuario(Model model, Principal principal) {
        // model.addAttribute("nombreUsuario", principal.getName()); // Obtiene el
        // nombre del usuario autenticado
        String nombreUsuario = principal.getName(); // Obtener el nombre del usuario
        UsuarioModel usuario = usuarioRepository.findByUsername(nombreUsuario); // Buscar el usuario en la base de datos
        model.addAttribute("usuario", usuario); // Pasar el usuario al modelo

        Long id = usuarioDAO.encontrarNombreById(nombreUsuario);
        List<PrestamoModel> lista = prestamoDAO.prestamosByID(id);
        model.addAttribute("lista", lista);

        return "dashboard_usuario"; // Nombre del archivo HTML
    }

}