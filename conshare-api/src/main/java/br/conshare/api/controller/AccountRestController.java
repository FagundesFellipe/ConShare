package br.conshare.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.conshare.api.service.UserService;
import br.conshare.model.entities.Usuario;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins= "*")
public class AccountRestController {
	
	
	@Autowired
	private UserService userSerive;
	
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestHeader("Authorization") String encodedData){
		
		System.out.println("Chegou a request com a Base 64: " + encodedData);
		
		
		Usuario user = userSerive.validateLogin(encodedData);
		// ate aqui foi 100% correto pq usei usuario lala e lele, os quais nao existem no seu db
		if(user == null) {
			return ResponseEntity.badRequest().build();
			
		}
		
		return ResponseEntity.ok(user);
			
		
	}
	

}
