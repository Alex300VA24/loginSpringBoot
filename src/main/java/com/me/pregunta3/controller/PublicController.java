package com.me.pregunta3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    @GetMapping("/mi-login")
    public String login() {
        return "mi-login"; // Redirige a login.html en templates
    }
}

