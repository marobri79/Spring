package com.marobri.tienda.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.marobri.tienda.model.entities.Autor;
import com.marobri.tienda.model.entities.Pedido;
import com.marobri.tienda.service.CarritoService;
import com.marobri.tienda.service.PedidosService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {
	
	@Autowired
	private PedidosService pedidosService;
	@Autowired
	private CarritoService carritoService;

	@GetMapping
	@ResponseBody
	public List<Pedido> getPedidos() {
		List<Pedido> pedidos = pedidosService.getPedidos();
		return pedidos;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public Pedido getPedidoByID(@PathVariable("id") Long id) {
		Pedido p;
		try {
			p = pedidosService.buscarPorID(id);
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
		
		if (p == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado!");
		}

		return p;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Pedido createPedido(@RequestBody Pedido pedido) {
		try {
			Pedido p = pedidosService.crearPedido(pedido);
			carritoService.limpiarCarrito(pedido.getUsuario().getId());
			return p;
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	
	@PutMapping(value = "/{id}/enviar")
	@ResponseBody
	public Pedido enviarPedido(@PathVariable("id") Long id) {
		try {
			Pedido p = pedidosService.enviarPedido(id);
			if (p == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no existente!");
			}
			
			return p;
		} catch (ExcepcionPrivilegios e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
}

