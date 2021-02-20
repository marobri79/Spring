package com.marobri.tienda.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class ComprobadorPrivilegios {

	public boolean tienePrivilegio(String nombrePrivilegio, Collection<? extends GrantedAuthority> authorities) {
        // Java 8 Sintaxis
		// return authorities.stream().map(authority -> authority.getAuthority()).collect(Collectors.toList()).contains(nombrePrivilegio);
        
		// Java 6
        List<String> privilegiosEnFormatoString = new ArrayList<String>();
        for(GrantedAuthority ga : authorities) {
        	privilegiosEnFormatoString.add(ga.getAuthority());
        }
        
        return privilegiosEnFormatoString.contains(nombrePrivilegio);
	}
	
	public boolean tienePrivilegio(Collection<String> nombresPrivilegios, Collection<? extends GrantedAuthority> authorities) {
		List<String> privilegiosEnFormatoString = new ArrayList<String>();
        for(GrantedAuthority ga : authorities) {
        	privilegiosEnFormatoString.add(ga.getAuthority());
        }
        
        return privilegiosEnFormatoString.containsAll(nombresPrivilegios);
	}

}
