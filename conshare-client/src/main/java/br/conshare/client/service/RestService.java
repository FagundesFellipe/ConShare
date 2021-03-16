package br.conshare.client.service;

import java.util.Base64;

import org.springframework.http.HttpHeaders;

public class RestService {
	
	
	public static HttpHeaders getAuthenticationHeaders(String username, String password) {
		
		String auth = "Username=" + username + ";Password=" +password;
		
		byte[] encodedBytes;
		
		try {
			encodedBytes =  Base64.getEncoder().encode(auth.getBytes("utf-8"));
			
			System.out.println("encoded Bytes" + new String(encodedBytes));
			
			
			String header = "Basic " + new String(encodedBytes);
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", header );
			
			return headers;
			
			
		} catch (Exception e) {
			return null;
		}
	}

}
