package br.com.hotelalura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.hotelalura.modelo.Reservas;


public class ReservasDAO {
	
	private Connection connection;

	public ReservasDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void registroReservas(Reservas reserva) {
		try {
			String sql = "INSERT INTO RESERVAS(DATAENTRADA, DATASAIDA,VALOR, FORMAPAGAMENTO)"
					+ "VALUES(?, ? , ?, ?)";
			try(PreparedStatement pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
				pstm.setObject(1, reserva.getDataEntrada());
				pstm.setObject(2, reserva.getDataSaida());
				pstm.setString(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaPagamento());
				pstm.execute();
				
				try(ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reservas> registroDasReservas() {
		
		 try {	
			 List<Reservas> reserva = new ArrayList<Reservas>();
			 String sql = "SELECT ID, DATAENTRADA, DATASAIDA, VALOR, FORMAPAGAMENTO FROM RESERVAS" ;
			 		
			 try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				 pstm.execute();

				 transformarResultadosEmReservas(reserva, pstm);
			 }
			 	return reserva;
		  }catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
	

	public List<Reservas> pesquisarReservas(String busca){
		List<Reservas> reserva = new ArrayList<Reservas>();
		 try {	
			 
			 String sql = "SELECT ID, DATAENTRADA, DATASAIDA, VALOR, FORMAPAGAMENTO FROM RESERVAS WHERE ID = ? OR FORMAPAGAMENTO = ?" ;
			 		
			 try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				 pstm.setString(1, busca);
				 pstm.setString(2, busca);
				 pstm.execute();

				 transformarResultadosEmReservas(reserva, pstm);
			 }
			 	return reserva;
		  }catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
	
	public void excluirReservas(Integer id)  {
		 try {
	 			Statement stm = connection.createStatement();
	 			stm.execute("SET FOREIGN_KEY_CHECKS=0");
	 			PreparedStatement pstm = connection.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?"); 
	 			pstm.setInt(1, id);
	 			pstm.execute();
	 			stm.execute("SET FOREIGN_KEY_CHECKS=1");
		 }catch(SQLException e) {
				throw new RuntimeException(e);
		  }
	}
		
	public void transformarResultadosEmReservas(List<Reservas> reserva, PreparedStatement pstm) {
	
     try {
		try(ResultSet rst = pstm.getResultSet()){
			while(rst.next()){
				Integer id = rst.getInt("id");
				LocalDate dataEntrada = rst.getDate("DATAENTRADA").toLocalDate().plusDays(1);
				LocalDate dataSaida = rst.getDate("DATASAIDA").toLocalDate().plusDays(1);
				String valor = rst.getString("VALOR");
				String pagamentoForma = rst.getString("FORMAPAGAMENTO");
				
				Reservas registro = new Reservas(id, dataEntrada, dataSaida, valor, pagamentoForma);
				reserva.add(registro);
				
			}
		}
     }catch(SQLException e) {
    	 throw new RuntimeException(e);
     }
		
	}
	
	
	
	public void editarDadosReservas (LocalDate dataEntrada, LocalDate dataSaida, String valor, 
			String formaPagamento, Integer id){
		
			try (PreparedStatement stm = connection
					.prepareStatement("UPDATE RESERVAS  SET DATAENTRADA = ?, DATASAIDA = ?,"
							+ " VALOR = ?, FORMAPAGAMENTO = ? WHERE ID = ?")) {
				stm.setObject(1, java.sql.Date.valueOf(dataEntrada));
				stm.setObject(2, java.sql.Date.valueOf(dataSaida));
				stm.setString(3, valor );
				stm.setString(4, formaPagamento);
				stm.setInt(5, id);

				stm.execute();
			}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
