package br.com.hotelalura.controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import br.com.hotelalura.connectionfactory.ConnectionFactory;
import br.com.hotelalura.dao.HospedesDAO;
import br.com.hotelalura.modelo.Hospedes;

public class HospedesController {

	private HospedesDAO hospedesDao;
	
	public HospedesController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.hospedesDao = new HospedesDAO(connection);
	}
	
	public void registrarHospedes(Hospedes hospede) {
		this.hospedesDao.registrarHospede(hospede);
	}

	public List<Hospedes> registroHospedes() {
		return this.hospedesDao.registroDosHospedes();
	}
	
	public List<Hospedes> hospedesBuscar(String reservaId) {
		return this.hospedesDao.buscarDadosHospedes(reservaId);
	}
	
	public void editarDadosHospedes(String nome, String sobrenome,String documentos, LocalDate dataNascimento, String nacionalidade, 
			String telefone,Integer reservaId, Integer id) {
			this.hospedesDao.editarHospedes(nome, sobrenome, documentos, dataNascimento, nacionalidade, telefone,reservaId, id);
	}
	
	public void excluirHospede(Integer reservaId) {
		this.hospedesDao.excluirHospede(reservaId);
	}

	public void setVisible(boolean b) {
		
	}
	
}
