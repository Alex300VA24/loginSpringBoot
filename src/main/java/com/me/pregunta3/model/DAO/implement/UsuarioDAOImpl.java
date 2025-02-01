package com.me.pregunta3.model.DAO.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.me.pregunta3.model.DAO.interfaces.IUsuarioDAO;
import com.me.pregunta3.model.models.UsuarioModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UsuarioDAOImpl implements IUsuarioDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<UsuarioModel> listarUsuarios() {
        return this.entityManager.createQuery("SELECT u FROM UsuarioModel u").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioModel> listarUsuarioId(Long id) {
        return Optional.ofNullable(this.entityManager.find(UsuarioModel.class, id));
    }

    @Override
    @Transactional
    public void crearUsuario(UsuarioModel usuarioModel) {
        this.entityManager.persist(usuarioModel);
        this.entityManager.flush();
    }

    @Override
    @Transactional
    public void actualizarUsuario(UsuarioModel usuarioModel) {
        this.entityManager.merge(usuarioModel);
    }

    @Override
    @Transactional
    public void eliminarUsuario(UsuarioModel usuarioModel) {
        this.entityManager.remove(usuarioModel);
    }

}
