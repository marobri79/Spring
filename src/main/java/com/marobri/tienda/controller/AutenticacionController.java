package com.marobri.tienda.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.marobri.tienda.config.JwtToken;
import com.marobri.tienda.dto.JwtRequest;
import com.marobri.tienda.dto.JwtResponse;
import com.marobri.tienda.dto.UsuarioDTO;
import com.marobri.tienda.model.entities.Usuario;
import com.marobri.tienda.service.JwtUserDetailsService;
import com.marobri.tienda.service.UsuariosService;

import javax.transaction.Transactional;
import javax.validation.Valid;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/api/autenticacion")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private UsuariosService usersService;
    
    @Autowired
    private ModelMapper modelMapper;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
    	
        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword(), userDetails.getAuthorities());

        Usuario user = usersService.findByNombreUsuario(authenticationRequest.getUsername()).get();
        final String token = jwtToken.generateToken(userDetails, user);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Transactional
    private void authenticate(String username, String password, Collection<? extends GrantedAuthority> authorities) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, authorities));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    
    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public ResponseEntity<?> createNuevoUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception {
    	
    	Usuario usuario = convertirUsuarioDtoToEntity(usuarioDTO);
    	Usuario usuarioCreado = null;
    	
    	usuarioCreado = usersService.crearUsuarioCliente(usuario);
    	return ResponseEntity.ok(convertirUsuarioToDto(usuarioCreado));
    }
    
    private UsuarioDTO convertirUsuarioToDto(Usuario user) {
    	UsuarioDTO userDto = modelMapper.map(user, UsuarioDTO.class);
        return userDto;
    }

    private Usuario convertirUsuarioDtoToEntity(UsuarioDTO userDto) {
    	Usuario user = modelMapper.map(userDto, Usuario.class);
        return user;
    }


}
