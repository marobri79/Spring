package com.marobri.tienda.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.marobri.tienda.model.entities.Funcionalidad;
import com.marobri.tienda.model.entities.Usuario;
import com.marobri.tienda.repository.UsuariosRepository;

@Component
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> user = usuariosRepository.findByNombreUsuario(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
            user.get().getNombre(),
            user.get().getClave(),
            getAuthorities(user.get().getFuncionalidades())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Funcionalidad> funcionalidades) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Funcionalidad funcionalidad: funcionalidades) {
           authorities.add(new SimpleGrantedAuthority(funcionalidad.getNombre()));
        }

        return authorities;
    }

}
