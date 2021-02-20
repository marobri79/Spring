package com.marobri.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.marobri.tienda.exception.ExcepcionPrivilegios;
import com.marobri.tienda.model.entities.Libro;
import com.marobri.tienda.repository.LibrosRepository;
import com.marobri.tienda.util.ComprobadorPrivilegios;
import com.marobri.tienda.util.Constantes;

@Service
public class LibrosService {
	

	@Autowired
	private ComprobadorPrivilegios comprobadorPrivilegios;
	
	@Autowired
	private LibrosRepository librosRepository;
	
	public List<Libro> obtenerLibros() throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.LIBRO_LECTURA, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.LIBRO_LECTURA);
		}
		
		List<Libro> results = new ArrayList<Libro>();
		Iterable<Libro> iterable = librosRepository.findAll();
		for(Libro l : iterable) {
			results.add(l);
		}
		
		return results;
	}
	
	public Optional<Libro> obtenerLibro(Long id) throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.LIBRO_LECTURA, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.LIBRO_LECTURA);
		}
		
		return librosRepository.findById(id);
	}
	
	public Optional<Libro> borrarLibroPorID(Long id) throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.LIBRO_BORRAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.LIBRO_BORRAR);
		}
		
		Optional<Libro> opt = obtenerLibro(id);
		if (opt.isPresent()) {
			Libro l = opt.get();
			l.setBorrado(true);
			librosRepository.save(l);
		}
		
		return opt;
	}

	public Libro crearLibro(Libro libro) throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.LIBRO_CREAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.LIBRO_CREAR);
		}
		
		return librosRepository.save(libro);
	}

	public Libro actualizarLibro(Libro libro) throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.LIBRO_EDITAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.LIBRO_EDITAR);
		}
		
		return librosRepository.save(libro);
	}

}
