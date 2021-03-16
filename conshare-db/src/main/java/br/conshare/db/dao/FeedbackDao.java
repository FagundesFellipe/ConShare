package br.conshare.db.dao;

import java.util.List;

import br.conshare.model.entities.Feedback;

public interface FeedbackDao {

	List<Feedback> readAll();

	Long create(Feedback entity);

	Feedback readById(Long id);

	boolean update(Feedback entity);

	boolean deleteById(Long Id);

}
