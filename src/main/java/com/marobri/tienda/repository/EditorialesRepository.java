package com.marobri.tienda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marobri.tienda.model.entities.Editorial;

@Repository
public interface EditorialesRepository extends CrudRepository<Editorial, Long> {

}
