package com.marobri.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.marobri.tienda.exception.ExcepcionPrivilegios;
import com.marobri.tienda.exception.ExcepcionServicio;
import com.marobri.tienda.model.entities.Editorial;
import com.marobri.tienda.repository.EditorialesRepository;
import com.marobri.tienda.util.ComprobadorPrivilegios;
import com.marobri.tienda.util.Constantes;

@Service
public class EditorialesService {
	

	@Autowired
	private ComprobadorPrivilegios comprobadorPrivilegios;
	
	@Autowired
	private EditorialesRepository editorialesRepository;
	
	public List<Editorial> obtenerEditoriales() throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.EDITORIAL_LECTURA, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.EDITORIAL_LECTURA);
		}
		
		List<Editorial> results = new ArrayList<Editorial>();
		Iterable<Editorial> iterable = editorialesRepository.findAll();
		for(Editorial l : iterable) {
			results.add(l);
		}
		
		return results;
	}
	
	public Optional<Editorial> obtenerEditorial(Long id) throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.EDITORIAL_LECTURA, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.EDITORIAL_LECTURA);
		}
		
		return editorialesRepository.findById(id);
	}
	
	public Optional<Editorial> borrarEditorialPorID(Long id) throws ExcepcionPrivilegios, ExcepcionServicio {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.EDITORIAL_BORRAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.EDITORIAL_BORRAR);
		}
		
		Optional<Editorial> opt = obtenerEditorial(id);
		if (opt.isPresent()) {
			if (opt.get().getLibros().size() > 0) {
				throw new ExcepcionServicio("La editorial tiene libros asociados, no se puede borrar.");
			}
			else {
				editorialesRepository.delete(opt.get());
			}
		}
		
		return opt;
	}

	public Editorial crearEditorial(Editorial editorial) throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.EDITORIAL_CREAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.EDITORIAL_CREAR);
		}
		
		return editorialesRepository.save(editorial);
	}

	public Editorial actualizarEditorial(Editorial editorial) throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.EDITORIAL_EDITAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.EDITORIAL_EDITAR);
		}
		
		return editorialesRepository.save(editorial);
	}

}
