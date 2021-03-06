package br.conshare.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.conshare.db.connecction.ConnectionFactory;
import br.conshare.db.dao.AnswerDao;
import br.conshare.model.entities.Respostas;

@Repository
public class AnswerDaoImpl implements AnswerDao {
	
	public static Long USUARIO_ID = Long.valueOf(1);
	public static Long DUVIDA_ID = Long.valueOf(2);

	@Override
	public List<Respostas> readAll() {
		
		List <Respostas> respostas = new ArrayList<Respostas>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			String sql = "SELECT * FROM resposta_duvida;";
			
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Respostas resposta = new Respostas ();
				resposta.setId(resultSet.getLong("id"));
				resposta.setData_hora(resultSet.getTimestamp("data_hora"));
				resposta.setUsuario_id(resultSet.getLong("usuario_id"));
				resposta.setTexto(resultSet.getString("texto"));

				
				respostas.add(resposta);
			}
		} catch (Exception e) {
			
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		return respostas;
		
		
	}

	@Override
	public Long create(Respostas entity) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "INSERT INTO resposta_duvida(duvida_id, usuario_id, data_hora, texto)" ;
		sql += " VALUES(?, ?, ?, ?);";
		
		Long id = Long.valueOf(0);
		
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setLong(1, entity.getDuvida_id());
			preparedStatement.setLong(2, entity.getUsuario_id());
			preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setString(4, entity.getTexto());
			
	
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return id;
			
		} finally {
			ConnectionFactory.close(resultSet,preparedStatement, connection);
			
		}
		
		
		
		
		
	}

	@Override
	public Respostas readById(Long id) {
		
		Respostas resposta = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			
			String sql = "SELECT * FROM resposta_duvida WHERE id = ? ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				resposta = new Respostas();
				resposta.setTexto(resultSet.getString("texto"));

			}
			
			
			
		} catch (Exception e) {
			
		} finally {
			 ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		return resposta;
		
		
		
		
	}

	@Override
	public boolean update(Respostas entity) {
		
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "UPDATE resposta_duvida SET texto = ?, data_hora = ?";
		sql += "WHERE id = ?; ";
		
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, entity.getTexto());
			preparedStatement.setTimestamp(2, entity.getData_hora());

			
			preparedStatement.execute();
			connection.commit();
			
			return true;
			

			
		} catch (Exception e) {
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return false;
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
			
		}
		
	}

	@Override
	public boolean deleteById(Long Id) {
		
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "DELETE FROM resposta_duvida WHERE id = ?";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setLong(1, Id);
			
			preparedStatement.execute();
			connection.commit();
			
			return true;
			
					
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return false;
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		

	
	}
	

}
