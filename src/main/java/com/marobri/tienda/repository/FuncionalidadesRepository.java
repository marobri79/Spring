package com.marobri.tienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marobri.tienda.model.entities.Funcionalidad;

@Repository
public interface FuncionalidadesRepository extends CrudRepository<Funcionalidad, Long> {

	
	@Query("SELECT f FROM Funcionalidad f WHERE f.nombre = :nombre")
    public Optional<Funcionalidad> findByName(@Param("nombre") String nombre);
	
}
