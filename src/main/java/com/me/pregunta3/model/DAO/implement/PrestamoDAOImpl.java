package com.me.pregunta3.model.DAO.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.me.pregunta3.model.DAO.interfaces.IPrestamoDAO;
import com.me.pregunta3.model.models.PrestamoModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PrestamoDAOImpl implements IPrestamoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<PrestamoModel> listarPrestamos() {
        return this.entityManager.createQuery("SELECT u FROM PrestamoModel u").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PrestamoModel> listarPrestamoId(Long id) {
        return Optional.ofNullable(this.entityManager.find(PrestamoModel.class, id));
    }

    @Override
    @Transactional
    public void crearPrestamo(PrestamoModel prestamoModel) {
        this.entityManager.persist(prestamoModel);
        this.entityManager.flush();
    }

    @Override
    @Transactional
    public void actualizarPrestamo(PrestamoModel prestamoModel) {
        this.entityManager.merge(prestamoModel);
    }

    @Override
    @Transactional
    public void eliminarPrestamo(PrestamoModel prestamoModel) {
        this.entityManager.remove(prestamoModel);
    }

    // Método para buscar un préstamo activo por ID de libro
    public Optional<PrestamoModel> buscarPrestamoActivo(Long idLibro) {
        try {
            // Consulta para verificar si existe un préstamo activo para ese libro
            String query = "SELECT p FROM PrestamoModel p WHERE p.idLibro.id = :idLibro " +
                    "AND (p.fechaDevolucion IS NULL OR p.fechaDevolucion > CURRENT_DATE) " +
                    "AND p.estado = true";

            // Ejecutar la consulta
            PrestamoModel prestamoActivo = entityManager.createQuery(query, PrestamoModel.class)
                    .setParameter("idLibro", idLibro)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);

            return Optional.ofNullable(prestamoActivo);

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el préstamo activo: " + e.getMessage());
        }
    }

}
