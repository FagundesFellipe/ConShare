package br.conshare.client.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.conshare.client.security.CustomUserDetails;
import br.conshare.client.service.UserService;
import br.conshare.model.entities.Usuario;

@Component
public class ConShareAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserService userServive;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		System.out.println("username: " + username + " senha: " + password);
		
		Usuario user = userServive.validateUsernameAndPassword(username, password);
		
		
		if(user == null) {
			return null;
		}
		
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
		grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + user.getTipo()));
		
		
		UserDetails principal = new CustomUserDetails(username, password , grantedAuthorityList, user);
		
		return new UsernamePasswordAuthenticationToken(principal, password, grantedAuthorityList);
	}

	@Override
	public boolean supports(Class<?> authentication) {

		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	
	public Usuario getAuthenticateduser() {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		return userDetails.getUsuario();
	} 

}
