package br.conshare.client.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.conshare.model.entities.Usuario;

public class CustomUserDetails extends User{

	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Usuario user) {
		
		super(username, password, true, true, true, true, authorities);
		
		this.user =user;
		
	}
	
	private Usuario user;

	public Usuario getUsuario() {
		return user;
	}


	
	
	

}
