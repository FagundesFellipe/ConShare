package br.conshare.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.conshare.db.connecction.ConnectionFactory;
import br.conshare.db.dao.FeedbackDao;
import br.conshare.model.entities.Feedback;

@Repository
public class FeedbackDaoImpl implements FeedbackDao {

public Long create(Feedback entity) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "INSERT INTO feedback(informacao_adicional, nota) ";
		sql += "VALUES(?, ?);";
		
		Long id = Long.valueOf(1);

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getInformacao_adicional());
			preparedStatement.setInt(2, entity.getNota());

			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();
			
			if(resultSet.next()) {
				id = resultSet.getLong(1);
				
			}
			connection.commit();

			return id;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return id;

		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}
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
