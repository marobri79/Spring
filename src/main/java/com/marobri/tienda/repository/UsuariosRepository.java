package com.marobri.tienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marobri.tienda.model.entities.Usuario;

@Repository
public interface UsuariosRepository extends CrudRepository <Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u WHERE u.nombre = :nombreUsuario")
    public Optional<Usuario> findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
	

}
