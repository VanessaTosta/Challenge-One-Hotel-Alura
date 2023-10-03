package br.com.hotelalura.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.hotelalura.connectionfactory.ConnectionFactory;

public class UsuarioLogin {

	
	private String usuario;
	private String senha;
	
	
	public UsuarioLogin(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public static boolean validarLogin(String nome, String senha) {
		ConnectionFactory connection = new ConnectionFactory();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rst = null;
		try {
			
			con = connection.recuperarConexao();
			pstm = con.prepareStatement("SELECT * FROM LOGIN WHERE USUARIO = ? "
					+ "AND SENHA = ?");
			pstm.setString(1, nome);
			pstm.setString(2, senha);
			
			rst = pstm.executeQuery();
			
			return rst.next();
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(rst !=null) 
					rst.close();
				if(pstm !=null)
					pstm.close();
				if(con != null)
					con.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	

}
