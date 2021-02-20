package com.marobri.tienda.util;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marobri.tienda.model.entities.Funcionalidad;
import com.marobri.tienda.model.entities.Usuario;
import com.marobri.tienda.repository.FuncionalidadesRepository;
import com.marobri.tienda.service.UsuariosService;

@Component
public class CargadorMaestrosBD {

	@Autowired
	private UsuariosService usuariosService;
	
	@Autowired
	private FuncionalidadesRepository funcionalidadesRepository;

	@Transactional
	public void precargar() {
		
		/* PRECARGA FUNCIONALIDADES */
		Set<Funcionalidad> funcionalidades = Constantes.FUNCIONALIDADES_PARA_ADMIN.stream()
				.map(nombreFuncionalidad -> new Funcionalidad(nombreFuncionalidad))
				.collect(Collectors.toSet());
		
		for(Funcionalidad func : funcionalidades) {
			Optional<Funcionalidad> optionalFuncionalidad = funcionalidadesRepository.findByName(func.getNombre());
			if (!optionalFuncionalidad.isPresent()) {
				funcionalidadesRepository.save(func);
			}
		}
		
		/* PRECARGA USUARIO ADMIN POR DEFECTO */
		Optional<Usuario> optUsuarioAdmin = usuariosService.findByNombreUsuario("admin");
		if (!optUsuarioAdmin.isPresent()) {
			Usuario usuarioAdmin = new Usuario();
			usuarioAdmin.setClave("1234");
			usuarioAdmin.setDireccion("empty");
			usuarioAdmin.setEmail("admin@example");
			usuarioAdmin.setNombre("admin");
			usuarioAdmin.setTelefono("123456789");
			usuariosService.crearUsuarioAdmin(usuarioAdmin);
		}
		
	}
	
}
