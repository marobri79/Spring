package com.marobri.tienda.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.marobri.tienda.exception.ExcepcionPrivilegios;
import com.marobri.tienda.model.entities.Pedido;
import com.marobri.tienda.model.entities.PedidoLibro;
import com.marobri.tienda.model.entities.Usuario;
import com.marobri.tienda.repository.PedidosRepository;
import com.marobri.tienda.repository.UsuariosRepository;
import com.marobri.tienda.util.ComprobadorPrivilegios;
import com.marobri.tienda.util.Constantes;

@Service
public class PedidosService {
	
	@Autowired
	private PedidosRepository pedidosRepository;
	@Autowired
	private UsuariosRepository usuariosRepository;
	@Autowired
	private ComprobadorPrivilegios comprobadorPrivilegios;
	
	public List<Pedido> getPedidos() {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		boolean mostrarTodosLosPedidos = comprobadorPrivilegios.tienePrivilegio(Constantes.PEDIDO_LECTURA, SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		if (mostrarTodosLosPedidos) {
			pedidosRepository.findAll().forEach(pedidos::add);
		}
		else {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			// Solo los del usuario conectado
			pedidosRepository.findByUserName(username).forEach(pedidos::add);
		}
		
		return pedidos;
	}
	
	public Pedido crearPedido(Pedido p) throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.PEDIDO_CREAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.PEDIDO_CREAR);
		}
		
		Set<PedidoLibro> lineas = p.getLineas();
		
		Set<PedidoLibro> l = new HashSet<PedidoLibro>();
		for (PedidoLibro pl : lineas) {
			PedidoLibro aux = pl;
			aux.setPedido(p);
			l.add(aux);
		}
		
		p.setLineas(l);
		pedidosRepository.save(p);
		return p;
	}

	public Pedido buscarPorID(Long id) throws ExcepcionPrivilegios {
		
		Pedido p = null;
		Optional<Pedido> optPedido = pedidosRepository.findById(id);
		if (optPedido.isPresent()) {
			p = optPedido.get();
			
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Optional<Usuario> optUsuario = usuariosRepository.findByNombreUsuario(username);
			
			if (optUsuario.get().getId().equals(p.getUsuario().getId())) {
				return p;
			}
			else {
				if (!comprobadorPrivilegios.tienePrivilegio(Constantes.PEDIDO_LECTURA, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
					throw new ExcepcionPrivilegios(Constantes.PEDIDO_LECTURA);
				}
				else {
					return p;
				}
			}
		}
		
		return null;
	}

	public Pedido enviarPedido(Long id) throws ExcepcionPrivilegios {
		
		Pedido p = null;
		Optional<Pedido> optPedido = pedidosRepository.findById(id);
		if (optPedido.isPresent()) {
			p = optPedido.get();
			
			if (!comprobadorPrivilegios.tienePrivilegio(Constantes.PEDIDO_EDITAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
				throw new ExcepcionPrivilegios(Constantes.PEDIDO_EDITAR);
			}
				
			// Le pongo a enviado y guardo
			p.setEnviado(true);
			pedidosRepository.save(p);
		}

		return p;
	}
	

}
