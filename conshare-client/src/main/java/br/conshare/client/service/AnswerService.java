package br.conshare.client.service;

import java.util.List;

import br.conshare.model.entities.Respostas;



public interface AnswerService {
	
	List<Respostas> readAll();
	
	Long create(Respostas entity);
	
	Respostas readById (Long id);
	
	boolean update (Respostas entity);
	
	boolean deleteById (Long Id);

}
