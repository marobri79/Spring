package com.marobri.tienda.controller;

import java.util.List;
import java.util.Optional;

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
import com.marobri.tienda.exception.ExcepcionServicio;
import com.marobri.tienda.model.entities.Autor;
import com.marobri.tienda.service.AutoresService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/autores")
public class AutoresController {
	
	@Autowired
	private AutoresService autoresService;

	@GetMapping
	@ResponseBody
	public List<Autor> getAutores() {
		List<Autor> autores;
		try {
			autores = autoresService.obtenerAutores();
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}

		return autores;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public Autor getAutorByID(@PathVariable("id") Long id) {
		Optional<Autor> optAutor;
		try {
			optAutor = autoresService.obtenerAutor(id);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
		
		if (!optAutor.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor no encontrado!");
		}

		return optAutor.get();
	}
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAutor(@PathVariable("id") Long id) {
		Optional<Autor> optAutorBorrado;
		try {
			optAutorBorrado = autoresService.borrarAutorPorID(id);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		} catch (ExcepcionServicio e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}
		if (!optAutorBorrado.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor no encontrado!");
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Autor createAutor(@RequestBody Autor autor) {
		try {
			return autoresService.crearAutor(autor);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Autor updateAutor(@RequestBody Autor autor) {
		try {
			return autoresService.actualizarAutor(autor);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}	
}

