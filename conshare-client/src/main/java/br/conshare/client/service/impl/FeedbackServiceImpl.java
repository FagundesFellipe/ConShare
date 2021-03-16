package br.conshare.client.service.impl;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.conshare.client.service.FeedbackService;
import br.conshare.model.entities.Feedback;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	
	@Override
	public Long create(Feedback entity) {
		
		Long id = Long.valueOf(1);
		
		String endpoint = "http://localhost:8085/api/v1/feedback/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			HttpEntity<Feedback> httpEntity = new HttpEntity<Feedback>(entity);
			
			ResponseEntity<Long> requestResponse = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Long.class);
			
			id = requestResponse.getBody();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		
		return id;
	}
	

	@Override
	public List<Feedback> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Feedback entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long Id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
