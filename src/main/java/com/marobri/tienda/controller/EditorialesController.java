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
import com.marobri.tienda.model.entities.Editorial;
import com.marobri.tienda.service.EditorialesService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/editoriales")
public class EditorialesController {
	
	@Autowired
	private EditorialesService editorialesService;

	@GetMapping
	@ResponseBody
	public List<Editorial> getEditoriales() throws ExcepcionPrivilegios {
		List<Editorial> editoriales;
		editoriales = editorialesService.obtenerEditoriales();

		return editoriales;
	}
	
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEditorial(@PathVariable("id") Long id) {
		Optional<Editorial> optEditorialBorrado;
		try {
			optEditorialBorrado = editorialesService.borrarEditorialPorID(id);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		} catch (ExcepcionServicio exS) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, exS.getMessage());
		}
		
		if (!optEditorialBorrado.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Editorial no encontrado!");
		}
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public Editorial getEditorialByID(@PathVariable("id") Long id) {
		Optional<Editorial> optEditorial;
		try {
			optEditorial = editorialesService.obtenerEditorial(id);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
		
		if (!optEditorial.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Editorial no encontrado!");
		}

		return optEditorial.get();
	}
		
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Editorial createEditorial(@RequestBody Editorial editorial) {
		try {
			return editorialesService.crearEditorial(editorial);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Editorial updateEditorial(@RequestBody Editorial editorial) {
		try {
			return editorialesService.actualizarEditorial(editorial);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

}

