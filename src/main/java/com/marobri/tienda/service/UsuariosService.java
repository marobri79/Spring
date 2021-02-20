package com.marobri.tienda.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marobri.tienda.exception.ExcepcionPrivilegios;
import com.marobri.tienda.model.entities.Autor;
import com.marobri.tienda.model.entities.Editorial;
import com.marobri.tienda.model.entities.Funcionalidad;
import com.marobri.tienda.model.entities.Pedido;
import com.marobri.tienda.model.entities.Usuario;
import com.marobri.tienda.repository.AutoresRepository;
import com.marobri.tienda.repository.UsuariosRepository;
import com.marobri.tienda.util.ComprobadorPrivilegios;
import com.marobri.tienda.util.Constantes;

@Service
public class UsuariosService {
	@Autowired
	private ComprobadorPrivilegios comprobadorPrivilegios;
	
    @Autowired
    private UsuariosRepository usersRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private FuncionalidadesService funcionalidadesService;

    public Optional<Usuario> findByNombreUsuario(String username) {
        return usersRepository.findByNombreUsuario(username);
    }

	public Usuario crearUsuarioCliente(Usuario usuario) {
		// TODO: Validaciones campos nombre y email (unicos)
		
		// Ciframos la password
		usuario.setClave(passwordEncoder.encode(usuario.getClave()));
		
		Set<Funcionalidad> funcionalidades = new HashSet<>();
		for(String sFuncionalidad : Constantes.FUNCIONALIDADES_PARA_CLIENTE) {
			Optional<Funcionalidad> optFuncionalidad = funcionalidadesService.buscarFuncionalidadPorNombre(sFuncionalidad);
			if (optFuncionalidad.isPresent()) {
				funcionalidades.add(optFuncionalidad.get());
			}
		}
		
		usuario.setFuncionalidades(funcionalidades);
		return usersRepository.save(usuario);
	}

	public Usuario crearUsuarioAdmin(Usuario usuario) {
		// TODO: Validaciones campos nombre y email (unicos)
		
		// Ciframos la password
		usuario.setClave(passwordEncoder.encode(usuario.getClave()));
		
		Set<Funcionalidad> funcionalidades = new HashSet<>();
		for(String sFuncionalidad : Constantes.FUNCIONALIDADES_PARA_ADMIN) {
			Optional<Funcionalidad> optFuncionalidad = funcionalidadesService.buscarFuncionalidadPorNombre(sFuncionalidad);
			if (optFuncionalidad.isPresent()) {
				funcionalidades.add(optFuncionalidad.get());
			}
		}
		
		usuario.setFuncionalidades(funcionalidades);
		return usersRepository.save(usuario);
	}

	public List<Usuario> obtenerUsuarios() throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.USUARIO_LECTURA, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.USUARIO_LECTURA);
		}
		
		List<Usuario> results = new ArrayList<Usuario>();
		Iterable<Usuario> iterable = usersRepository.findAll();
		for(Usuario l : iterable) {
			results.add(l);
		}
		
		return results;
	}
	
	public Usuario obtenerUsuario(Long id) throws ExcepcionPrivilegios {
		
		
		Usuario u = null;
		Optional<Usuario> optUser = usersRepository.findById(id);
		if (optUser.isPresent()) {
			u = optUser.get();
			
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Optional<Usuario> optUsuario = usersRepository.findByNombreUsuario(username);
			
			if (optUsuario.get().getId().equals(u.getId())) {
				return u;
			}
			else {
				if (!comprobadorPrivilegios.tienePrivilegio(Constantes.USUARIO_LECTURA, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
					throw new ExcepcionPrivilegios(Constantes.USUARIO_LECTURA);
				}
				else {
					return u;
				}
			}
		}
		
		return null;
	}
	
	public Usuario borrarUsuarioPorID(Long id) throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.USUARIO_BORRAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.USUARIO_BORRAR);
		}
		
		Usuario u = obtenerUsuario(id);
		if (u != null) {
			usersRepository.delete(u);
		}
		
		return u;
	}

	public Usuario crearUsuario(Usuario usuario) throws ExcepcionPrivilegios {
		
		if (!comprobadorPrivilegios.tienePrivilegio(Constantes.USUARIO_CREAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
			throw new ExcepcionPrivilegios(Constantes.USUARIO_CREAR);
		}
		
		return usersRepository.save(usuario);
	}

	public Usuario actualizarUsuario(Usuario usuario) throws ExcepcionPrivilegios {
		
		Usuario u = null;
		Optional<Usuario> optUser = usersRepository.findById(usuario.getId());
		if (optUser.isPresent()) {
			u = optUser.get();
			
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Optional<Usuario> optUsuario = usersRepository.findByNombreUsuario(username);
			
			u.setClave(passwordEncoder.encode(usuario.getClave()));
			
			if (optUsuario.get().getId().equals(u.getId())) {
				return usersRepository.save(u);
			}
			else {
				if (!comprobadorPrivilegios.tienePrivilegio(Constantes.USUARIO_EDITAR, SecurityContextHolder.getContext().getAuthentication().getAuthorities())) {
					throw new ExcepcionPrivilegios(Constantes.USUARIO_EDITAR);
				}
				else {
					return usersRepository.save(u);
				}
			}
		}
		
		return null;
	}
}
