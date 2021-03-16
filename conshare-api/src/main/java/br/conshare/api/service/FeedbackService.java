package br.conshare.api.service;

import java.util.List;

import br.conshare.model.entities.Feedback;

public interface FeedbackService {

	List<Feedback> readAll();

	Long create(Feedback entity);

	Feedback readById(Long id);

	boolean update(Feedback entity);

	boolean deleteById(Long Id);

}
