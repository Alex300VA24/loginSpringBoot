package com.me.pregunta3.model.DAO.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.me.pregunta3.model.DAO.interfaces.ILibroDAO;
import com.me.pregunta3.model.models.LibroModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class LibroDAOImpl implements ILibroDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<LibroModel> listarLibros() {
        return this.entityManager.createQuery("SELECT u FROM LibroModel u").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LibroModel> listarLibroId(Long id) {
        return Optional.ofNullable(this.entityManager.find(LibroModel.class, id));
    }

    @Override
    @Transactional
    public void crearLibro(LibroModel libroModel) {
        this.entityManager.persist(libroModel);
        this.entityManager.flush();
    }

    @Override
    @Transactional
    public void actualizarLibro(LibroModel libroModel) {
        this.entityManager.merge(libroModel);
    }

    @Override
    @Transactional
    public void eliminarLibro(LibroModel libroModel) {
        this.entityManager.remove(libroModel);
    }

}
