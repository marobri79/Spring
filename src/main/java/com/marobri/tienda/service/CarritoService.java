package com.marobri.tienda.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.marobri.tienda.exception.ExcepcionPrivilegios;
import com.marobri.tienda.model.Carrito;
import com.marobri.tienda.model.ItemCarrito;
import com.marobri.tienda.model.entities.Usuario;
import com.marobri.tienda.repository.LibrosRepository;
import com.marobri.tienda.repository.UsuariosRepository;

@Service
public class CarritoService {

	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Autowired
	private LibrosRepository librosRepository;
	
	// Mapa indexado por el ID numerico del usuario, y que tiene un carrito
	private static Map<Long, Carrito> CARRITOS = new HashMap<Long, Carrito>();

	
	public Carrito obtenerCarritoDelUsuario(Long idUsuario) throws ExcepcionPrivilegios {
		
		Optional<Usuario> optUsuario = usuariosRepository.findById(idUsuario);
		if (optUsuario.isPresent()) {
			// Sacar usuario conectado
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			
			Usuario usuario = optUsuario.get();
			if (usuario.getNombre().equals(username)) {
				Carrito carro = CARRITOS.getOrDefault(idUsuario, new Carrito());
				return carro;
			}
		}

		throw new ExcepcionPrivilegios("No puede consultar dicho carrito");
	}
	
	public Carrito actualizarCarrito(Long idUsuario, ItemCarrito itemCarrito) throws ExcepcionPrivilegios {
		
		if (librosRepository.findById(itemCarrito.getIdLibro()).isPresent()) {
			Carrito carro = obtenerCarritoDelUsuario(idUsuario);
			
			boolean itemEncontrado = false;
			
			List<ItemCarrito> lineas = carro.getLineasCarrito();

			for(int i = 0; i < lineas.size() && !itemEncontrado; i++) {
				ItemCarrito linea = lineas.get(i);
				if (linea.getIdLibro() == itemCarrito.getIdLibro()) {
					
					if (itemCarrito.getCantidad() == 0) {
						lineas.remove(i);
					}
					else if (itemCarrito.getCantidad() > 0) {
						linea.setCantidad(itemCarrito.getCantidad());
					}
					
					itemEncontrado = true;
				}
			}
			
			if (!itemEncontrado && itemCarrito.getCantidad() > 0) {
				carro.getLineasCarrito().add(itemCarrito);
			}

			CARRITOS.put(idUsuario, carro);
			return carro;
		}
		
		throw new ExcepcionPrivilegios("Libro inexistente");
	}
	
	public void limpiarCarrito(Long idUsuario) {
		CARRITOS.remove(idUsuario);
	}
	
}
