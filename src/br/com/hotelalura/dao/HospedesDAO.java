

package br.com.hotelalura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
			
			String sql = "INSERT INTO HOSPEDES (NOME, SOBRENOME, DOCUMENTOS, DATANASCIMENTO, NACIONALIDADE, TELEFONE,"
					+ "RESERVA_ID ) VALUES (?, ?, ?, ?, ?, ?, ?)";
			

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, hospede.getNome());
				pstm.setString(2, hospede.getSobrenome());
				pstm.setString(3, hospede.getDocumentos());
				pstm.setObject(4, hospede.getDataNascimento());
				pstm.setString(5, hospede.getNacionalidade());
				pstm.setString(6, hospede.getTelefone());
				pstm.setInt(7, hospede.getReservaId());
				

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
			 String sql = "SELECT * FROM HOSPEDES";

			 try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				 pstm.execute();

				 trasformarResultSetEmHospedes(hospede, pstm);
			 }
			 	return hospede;
		  }catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
	
	
	public List<Hospedes> buscarDadosHospedes(String buscar) {
		
		List<Hospedes> hospede = new ArrayList<Hospedes>();
		try {	
			 
			 String sql = "SELECT * FROM HOSPEDES WHERE RESERVA_ID = ? OR SOBRENOME LIKE ? OR NOME LIKE ? OR NACIONALIDADE = ?";

			 try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				 pstm.setString(1, buscar);
				 pstm.setString(2, buscar + '%');
				 pstm.setString(3, buscar + '%');
				 pstm.setString(4, buscar);
				 pstm.execute();

				 trasformarResultSetEmHospedes(hospede, pstm);
			 }
			 	return hospede;
		  }catch(SQLException e) {
				throw new RuntimeException(e);
			}
	}
	

	public void excluirHospede(Integer reservaId)  {
	 try {
			try (PreparedStatement stm = connection.prepareStatement("DELETE FROM HOSPEDES WHERE ID = ?")) {
				stm.setInt(1, reservaId);
				stm.execute();
			}
	 }catch(SQLException e) {
			throw new RuntimeException(e);
	  }
	}
	
	
	private void trasformarResultSetEmHospedes(List<Hospedes> hospede, PreparedStatement pstm) {
			
		try (ResultSet rst = pstm.executeQuery()) {
				while (rst.next()) {
					int id = rst.getInt("id");
					String nome = rst.getString("nome");
					String sobrenome = rst.getString("sobrenome");
					String documentos= rst.getString("documentos");
					LocalDate dataNascimento = rst.getDate("dataNascimento").toLocalDate().plusDays(1);
					String nacionalidade = rst.getString("nacionalidade");
					String telefone = rst.getString("telefone");
					int reservaId = rst.getInt("reserva_Id");
					
					
					Hospedes hospedes= new Hospedes(id,nome,sobrenome,documentos,dataNascimento,nacionalidade,telefone,reservaId);
					hospede.add(hospedes);
				}		
			}catch(SQLException e) {
			throw new RuntimeException(e);
			}
			
	}

	public void editarHospedes(String nome, String sobrenome, String documentos, LocalDate dataNascimento, String nacionalidade,
			String telefone, Integer reservaId, Integer id) {
		try {
			try (PreparedStatement stm = connection
					.prepareStatement("UPDATE HOSPEDES SET NOME = ?, SOBRENOME = ?, DOCUMENTOS = ?, DATANASCIMENTO = ?,"
							+ "NACIONALIDADE = ?, TELEFONE = ?, RESERVA_ID = ? WHERE ID = ?")) {
				stm.setString(1, nome);
				stm.setString(2, sobrenome);
				stm.setString(3, documentos);
				stm.setObject(4, dataNascimento );
				stm.setString(5, nacionalidade);
				stm.setString(6, telefone);
				stm.setInt(7, reservaId);
				stm.setInt(8, id);
				

				stm.execute();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
	
	
 
	
	
	

	


	
	

