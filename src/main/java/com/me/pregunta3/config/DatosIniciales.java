package com.me.pregunta3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DatosIniciales {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void inti() {

        String query = "INSERT INTO usuarios (username, email, password, role) VALUES ('Alexander', 'alex234@gmail.com','$2a$12$.b5.p4NvOvp9OrlA9fOvxOao3Eh30e0gbhDngWVweixC27mEv/2PK', 'ADMIN'), ('Gustavo', 'gustav@gmail.com','$2a$12$YfumRbzriV4YVl/mOwpCTOLRDMTNY7WgziYkE4PEfKfBQPkhX2NYy', 'USER');";
        jdbcTemplate.update(query);

        query = "INSERT INTO libros (nombre, autor, disponible) VALUES ('Paco Yunque', 'Cesar Vallejo', true), ('Cien Años de Soledad', 'Gabriel García Marquez', true), ('El Mundo es Ancho y Ajeno', 'Ciro Alegria', true);";
        jdbcTemplate.update(query);

        query = "INSERT INTO prestamos (id_usuario, id_libro, fecha_prestamo, estado) VALUES (2, 1, '2025-02-01', true), (2, 2, '2025-01-30', false);";
        jdbcTemplate.update(query);

    }
}
