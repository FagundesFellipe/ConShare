package br.conshare.api.service.impl;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.conshare.api.service.UserService;
import br.conshare.db.dao.UserDao;
import br.conshare.model.entities.Usuario;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<Usuario> readAll() {
		
		return userDao.readAll();
	}

	@Override
	public Long create(Usuario entity) {
		
		return userDao.create(entity);
	}

	@Override
	public Usuario readById(Long id) {
		
		return userDao.readById(id);
	}

	@Override
	public boolean update(Usuario entity) {
		
		return userDao.update(entity);
	}

	@Override
	public boolean deleteById(Long Id) {
		
		return userDao.deleteById(Id);
	}

	@Override
	public Usuario validateUsernameAndPassword(String username, String password) {
		
		return null;
	}

	@Override
	public Usuario validateLogin(String encodedData) {
		
		
		Map<CREDENTIALS, String> credentialsMap = decodeAndGetUsernameAndPassword(encodedData);
		
		if(credentialsMap == null || credentialsMap.size() != 2) {
			return null;
		}
		
		String username = credentialsMap.get(CREDENTIALS.USERNAME);
		String password = credentialsMap.get(CREDENTIALS.PASSWORD);
		
		
		Usuario user = userDao.validadeUsernameAndPassword(username, password);
		
		if(user == null) {
			return null;
		}
		
		
		
		
		
		return user;
	}
	
	private enum  CREDENTIALS {
		USERNAME,
		PASSWORD
	}
	
	
	private Map<CREDENTIALS, String> decodeAndGetUsernameAndPassword(String encodedData){
		
		try {
			// pode tentar agora
			String[] splitData =  encodedData.split("Basic "); // faltou o espaco depois do Basic _ <-
			if(splitData.length != 2) {
				return null;
			}
			
			byte[] decodeBytes = Base64.getDecoder().decode(splitData[1]);
			
			String decodeString = new String(decodeBytes, "utf-8");
			
			String [] firstPart = decodeString.split("Username=");
			if(firstPart.length != 2) {
				return null;
			}
			
			
			String[] credentials = firstPart[1].split(";Password=");
			if(credentials.length != 2) {
				return null;
			}
			
			
			Map<CREDENTIALS, String> credentialsMap = new HashMap<CREDENTIALS, String>(); 	
			
			credentialsMap.put(CREDENTIALS.USERNAME, credentials[0]);
			credentialsMap.put(CREDENTIALS.PASSWORD, credentials[1]);
			
			return credentialsMap;
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}


	

	

}
