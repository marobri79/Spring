package com.marobri.tienda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marobri.tienda.model.entities.Autor;

@Repository
public interface AutoresRepository extends CrudRepository<Autor, Long> {

}
