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

import com.me.pregunta3.model.DTO.LibroDTO;
import com.me.pregunta3.model.DTO.services.interfaces.ILibroServicio;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private ILibroServicio libroServicio;

    @GetMapping("/listar")
    public String listarLibros(Model model) {
        List<LibroDTO> lista = libroServicio.listarLibros();
        model.addAttribute("lista", lista);

        return "libros_listar";
    }

    @GetMapping("/listar/{id}")
    public String listarLibroId(@PathVariable Long id, Model model) {

        LibroDTO libro = libroServicio.listarLibroId(id);
        model.addAttribute("libro", libro);

        return "libro_listar_id";
    }

    @GetMapping("/crear_form")
    public String crearLibroForm(LibroDTO libroDTO) {
        return "libro_crear";
    }

    @PostMapping("/crear")
    public String crearLibro(@ModelAttribute LibroDTO libroDTO) {
        libroServicio.crearLibro(libroDTO);

        return "redirect:/libros/listar";
    }

    @GetMapping("/actualizar_form/{id}")
    public String actulizarLibroForm(@PathVariable Long id, Model model) {
        LibroDTO libroDTO = libroServicio.listarLibroId(id);
        model.addAttribute("libro", libroDTO);

        return "libro_actulizar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarLibro(@PathVariable Long id, @ModelAttribute LibroDTO libroDTO) {
        libroServicio.actualizaLibro(id, libroDTO);

        return "redirect:/libros/listar";
    }

    @GetMapping("/eliminar_form")
    public String eliminarLibroForm(Model model) {
        List<LibroDTO> lista = libroServicio.listarLibros();
        model.addAttribute("lista", lista);
        return "libro_eliminar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libroServicio.eliminarLibro(id);

        return "redirect:/libros/listar";
    }

}
