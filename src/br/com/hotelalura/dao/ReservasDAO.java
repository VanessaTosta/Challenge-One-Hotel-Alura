package br.com.hotelalura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReservasDAO {
	
	private Connection connection;

	/*public reservaDAO(Connection connection) {
		this.connection = connection;
	}

	public List<FormadePagamento> listar() {
	   
		try {
			List<FormadePagamento> formapagamento = new ArrayList<>();
			String sql = "SELECT FORMAPAGAMENTO FROM RESERVAS";

				try (PreparedStatement pstm = connection.prepareStatement(sql)) {
					pstm.execute();

					try (ResultSet rst = pstm.getResultSet()) {
						while (rst.next()) {
							Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));

							categorias.add(categoria);
						}
					}
				}
				 return formapagamento;
		 } catch(SQLException e ) {
			
			 throw new RuntimeException(e);
		   }
		
	}*/
}
