package com.marobri.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marobri.tienda.model.entities.Pedido;

@Repository
public interface PedidosRepository extends CrudRepository <Pedido, Long> {
	
	@Query("SELECT p FROM Pedido p WHERE p.usuario.nombre = :usuarioNombre")
	public List<Pedido> findByUserName(@Param("usuarioNombre") String userName);
	
}	
