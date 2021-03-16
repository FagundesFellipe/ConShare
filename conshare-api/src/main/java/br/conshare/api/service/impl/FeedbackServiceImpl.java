package br.conshare.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.conshare.api.service.FeedbackService;
import br.conshare.db.dao.FeedbackDao;
import br.conshare.model.entities.Feedback;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public List<Feedback> readAll() {
		return feedbackDao.readAll();
	}

	@Override
	public Long create(Feedback entity) {
		return feedbackDao.create(entity);
	}

	@Override
	public Feedback readById(Long id) {
		return feedbackDao.readById(id);
	}

	@Override
	public boolean update(Feedback entity) {
		return feedbackDao.update(entity);
	}

	@Override
	public boolean deleteById(Long Id) {
		return feedbackDao.deleteById(Id);
	}

}
