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

import com.me.pregunta3.model.DTO.PrestamoDTO;
import com.me.pregunta3.model.DTO.services.interfaces.IPrestamoServicio;

@Controller
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private IPrestamoServicio prestamoServicio;

    @GetMapping("/listar")
    public String listarPrestamos(Model model) {
        List<PrestamoDTO> lista = prestamoServicio.listarPrestamos();
        model.addAttribute("lista", lista);

        return "prestamos_listar";
    }

    @GetMapping("/listar/{id}")
    public String listarPrestamoId(@PathVariable Long id, Model model) {

        PrestamoDTO prestamo = prestamoServicio.listarPrestamoId(id);
        model.addAttribute("prestamo", prestamo);

        return "prestamo_listar_id";
    }

    @GetMapping("/crear_form")
    public String crearPrestamoForm(PrestamoDTO prestamoDTO) {
        return "prestamo_crear";
    }

    @PostMapping("/crear")
    public String crearPrestamo(@ModelAttribute PrestamoDTO prestamoDTO) {
        prestamoServicio.crearPrestamo(prestamoDTO);

        return "redirect:/v1/admin";
    }

    @GetMapping("/actualizar_form/{id}")
    public String actulizarPrestamoForm(@PathVariable Long id, Model model) {
        PrestamoDTO prestamoDTO = prestamoServicio.listarPrestamoId(id);
        model.addAttribute("prestamo", prestamoDTO);

        return "prestamo_actualizar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPrestamo(@PathVariable Long id, @ModelAttribute PrestamoDTO prestamoDTO) {
        prestamoServicio.actualizarPrestamo(id, prestamoDTO);

        return "redirect:/v1/admin";
    }

    @GetMapping("/eliminar_form")
    public String eliminarPrestamo(Model model) {
        List<PrestamoDTO> lista = prestamoServicio.listarPrestamos();
        model.addAttribute("lista", lista);
        return "prestamo_eliminar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPrestamo(@PathVariable Long id) {
        prestamoServicio.eliminarPrestamo(id);

        return "redirect:/v1/admin";
    }

}
