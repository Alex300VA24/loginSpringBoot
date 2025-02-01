package com.me.pregunta3.repository;

import com.me.pregunta3.model.models.PrestamoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrestamoRepository extends CrudRepository<PrestamoModel, Long>{

    //PrestamoModel findById(Long id);
} 
