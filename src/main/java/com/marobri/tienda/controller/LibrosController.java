package com.marobri.tienda.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.marobri.tienda.exception.ExcepcionPrivilegios;
import com.marobri.tienda.model.entities.Libro;
import com.marobri.tienda.service.LibrosService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/libros")
public class LibrosController {

	@Autowired
	private LibrosService librosService;

	@GetMapping
	@ResponseBody
	public List<Libro> getLibros() {
		List<Libro> libros;
		try {
			libros = librosService.obtenerLibros();
			// Solo los activos
			libros = libros.stream().filter(item -> item.isBorrado() == false).collect(Collectors.toList());
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
		return libros;
	}

	@GetMapping(value = "/{id}")
	@ResponseBody
	public Libro getLibroByID(@PathVariable("id") Long id) {
		Optional<Libro> optLibro;
		try {
			optLibro = librosService.obtenerLibro(id);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
		
		if (!optLibro.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado!");
		}
		else {
			if (optLibro.get().isBorrado()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ya no vendemos este libro!");	
			}
			else {
				return optLibro.get();		
			}
		}
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLibro(@PathVariable("id") Long id) {
		Optional<Libro> optLibroBorrado;
		try {
			optLibroBorrado = librosService.borrarLibroPorID(id);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
		
		if (!optLibroBorrado.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado!");
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Libro createLibro(@RequestBody Libro libro) {
		try {
			return librosService.crearLibro(libro);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Libro updateLibro(@RequestBody Libro libro) {
		try {
			return librosService.actualizarLibro(libro);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
}
