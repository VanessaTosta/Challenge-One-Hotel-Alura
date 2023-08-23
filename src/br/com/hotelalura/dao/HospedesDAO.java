package br.com.hotelalura.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.hotelalura.modelo.Hospedes;

public class HospedesDAO {
	
	private Connection connection; 
	 
	public HospedesDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void registrarHospede(Hospedes hospede) {
		
		try {
			
			String sql = "INSERT INTO HOSPEDES (NOME, SOBRENOME, DATANASCIMENTO, NACIONALIDADE, TELEFONE"
					+ "RESERVA_ID ) VALUES (?, ?, ?, ?, ?, ?)";
			

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, hospede.getNome());
				pstm.setString(2, hospede.getSobrenome());
				pstm.setDate(3, hospede.getDatanascimento());
				pstm.setString(4, hospede.getNacionalidade());
				pstm.setString(5, hospede.getTelefone());
				pstm.setInt(6, hospede.getReservaId());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						hospede.setId(rst.getInt(1));
					}
				}
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Hospedes> registroDosHospedes() {
		
		 try {	
			 List<Hospedes> hospede = new ArrayList<Hospedes>();
			 String sql = "SELECT ID, NOME, SOBRENOME, DATANASCIMENTO, NACIONALIDADE, TELEFONE"
			 		+ "RESERVA_ID FROM HOSPEDES";

			 try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				 pstm.execute();

				 trasformarResultSetEmHospedes(hospede, pstm);
			 }
			 	return hospede;
		  }catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
	
	public void editarDadosHospedes (String nome, String sobrenome,Date dataNascimento,String nacionalidade,
			String telefone){
		try {
			try (PreparedStatement stm = connection
					.prepareStatement("UPDATE HOSPEDES H SET H.NOME = ?, H.SOBRENOME = ?, H.DATANASCIMENTO = ?,"
							+ "H.NACIONALIDADE = ?, H.TELEFONE = ? WHERE RESERVA_ID = ?")) {
				stm.setString(1, nome);
				stm.setString(2, sobrenome);
				stm.setDate(3, dataNascimento );
				stm.setString(4, nacionalidade);
				stm.setString(5, telefone);

				stm.execute();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void excluirHospede(Integer reservaId)  {
		 try {
	 		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM HOSPEDES WHERE RESERVA_ID = ?")) {
	 			stm.setInt(1, reservaId);
	 			stm.execute();
	 		}
		 }catch(SQLException e) {
				throw new RuntimeException(e);
		  }
	}
	
	public List<Hospedes> buscarHospedes(Hospedes h){
		
		 try{
			List<Hospedes> hospede = new ArrayList<Hospedes>();
			String sql = "SELECT ID, NOME, SOBRENOME, DATANASCIMENTO, NACIONALIDADE,"
					+ "TELEFONE,RESERVA_ID FROM HOSPEDES WHERE ? = ?";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, h.getId());
				pstm.setString(2, h.getNome());
				pstm.setString(3, h.getSobrenome());
				pstm.setDate(4, h.getDatanascimento());
				pstm.setString(5, h.getNacionalidade());
				pstm.setString(6, h.getTelefone());
				pstm.setInt(7, h.getReservaId());
				pstm.execute();

				trasformarResultSetEmHospedes(hospede, pstm);
			}
			return hospede;
		 }catch(SQLException e) {
				throw new RuntimeException(e);
			}
		 
	}

	

	private void trasformarResultSetEmHospedes(List<Hospedes> hospede, PreparedStatement pstm) {
		
		try {
			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Hospedes hospedes = new Hospedes(rst.getInt(1), rst.getString(2), rst.getString(3),rst.getDate(4),
							rst.getString(5), rst.getString(6), rst.getInt(7));

					hospede.add( hospedes);
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
}
