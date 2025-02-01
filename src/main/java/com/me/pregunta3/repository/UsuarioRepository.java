package com.me.pregunta3.repository;

import com.me.pregunta3.model.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {

    UsuarioModel findByUsername(String username);
}