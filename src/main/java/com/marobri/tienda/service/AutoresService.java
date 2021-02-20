package com.marobri.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.marobri.tienda.exception.ExcepcionPrivilegios;
import com.marobri.tienda.exception.ExcepcionServicio;
import com.marobri.tienda.model.entities.Autor;
import com.marobri.tienda.repository.AutoresRepository;
import com.marobri.tienda.util.ComprobadorPrivilegios;
import com.marobri.tienda.util.Constantes;

@Service
public class AutoresService {
	

	@Autowired
	private ComprobadorPrivilegios comprobadorPrivilegios;
	
	@Autowired
	private AutoresRepository AutoresRepository;
	
	public List<Autor> obtenerAutores() throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.AUTOR_LECTURA, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.AUTOR_LECTURA);
		}
		
		List<Autor> results = new ArrayList<Autor>();
		Iterable<Autor> iterable = AutoresRepository.findAll();
		for(Autor l : iterable) {
			results.add(l);
		}
		
		return results;
	}
	
	public Optional<Autor> obtenerAutor(Long id) throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.AUTOR_LECTURA, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.AUTOR_LECTURA);
		}
		
		return AutoresRepository.findById(id);
	}
	
	public Optional<Autor> borrarAutorPorID(Long id) throws ExcepcionPrivilegios, ExcepcionServicio {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.AUTOR_BORRAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.AUTOR_BORRAR);
		}
		
		Optional<Autor> opt = obtenerAutor(id);
		if (opt.isPresent()) {
			Autor autor = opt.get();
			if (autor.getLibros().isEmpty()) {
				AutoresRepository.delete(opt.get());
			} else {
				throw new ExcepcionServicio("No se puede borrar el autor, tiene libros");
			}
		}
		
		return opt;
	}

	public Autor crearAutor(Autor autor) throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.AUTOR_CREAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.AUTOR_CREAR);
		}
		
		return AutoresRepository.save(autor);
	}

	public Autor actualizarAutor(Autor autor) throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.AUTOR_EDITAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.AUTOR_EDITAR);
		}
		
		return AutoresRepository.save(autor);
	}

}
