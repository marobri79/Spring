package com.marobri.tienda.repository;

import org.springframework.stereotype.Repository;

import com.marobri.tienda.model.entities.Libro;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface LibrosRepository extends CrudRepository<Libro, Long> {
	
}
