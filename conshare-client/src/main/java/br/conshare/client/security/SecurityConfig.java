package br.conshare.client.security;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.conshare.client.security.provider.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private ConShareAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	      auth.authenticationProvider(authenticationProvider);

	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		     .authorizeRequests()
		     	.antMatchers("/resources/**").permitAll()
		     	.antMatchers("/").permitAll()
		     	.antMatchers("/access/register_user/**").permitAll()
		     	.antMatchers("/access/create/**").permitAll()
		     .anyRequest().authenticated()
		     
		     .and()
		     .formLogin()
		     	.loginPage("/access/login")
				.loginProcessingUrl("/login-test").permitAll()
				.defaultSuccessUrl("/collaboration/start")
			.and()
			
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/");
		
		
		
	}

}
