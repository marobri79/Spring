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
import com.marobri.tienda.model.Carrito;
import com.marobri.tienda.model.ItemCarrito;
import com.marobri.tienda.model.entities.Usuario;
import com.marobri.tienda.service.CarritoService;
import com.marobri.tienda.service.UsuariosService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

	@Autowired
	private CarritoService carritoService;
	@Autowired
	private UsuariosService usuariosService;
	
	@GetMapping(value = "/{id}/carrito")
	@ResponseBody
	public Carrito getCarritoDelUsuario(@PathVariable("id") Long idUsuario) {
		Carrito carro;
		try {
			carro = carritoService.obtenerCarritoDelUsuario(idUsuario);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
		return carro;
	}
	
	@PutMapping(value = "/{id}/carrito")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Carrito actualizarCarrito(@PathVariable("id") Long idUsuario, @RequestBody ItemCarrito itemCarrito) {
		try {
			return carritoService.actualizarCarrito(idUsuario, itemCarrito);
		} catch (ExcepcionPrivilegios e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}


	@GetMapping
	@ResponseBody
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios;
		try {
			usuarios = usuariosService.obtenerUsuarios();
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}

		return usuarios;
	}
	
		@GetMapping(value = "/{id}")
	@ResponseBody
	public Usuario getUsuarioByID(@PathVariable("id") Long id) {
		Usuario usuario = null;
		try {
			usuario  = usuariosService.obtenerUsuario(id);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
		
		if (usuario == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado!");
		}

		return usuario;
	}
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUsuario(@PathVariable("id") Long id) {
		Usuario usuarioBorrado;
		try {
			usuarioBorrado = usuariosService.borrarUsuarioPorID(id);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
		if (usuarioBorrado == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado!");
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Usuario createUsuario(@RequestBody Usuario usuario) {
		try {
			return usuariosService.crearUsuario(usuario);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Usuario updateUsuario(@RequestBody Usuario usuario ) {
		try {
			return usuariosService.actualizarUsuario(usuario);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}	
	
}
