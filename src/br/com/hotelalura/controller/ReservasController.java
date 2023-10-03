package br.com.hotelalura.controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import br.com.hotelalura.connectionfactory.ConnectionFactory;
import br.com.hotelalura.dao.ReservasDAO;
import br.com.hotelalura.modelo.Reservas;

public class ReservasController {
	private ReservasDAO reservasDao;
	
	public ReservasController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.reservasDao = new ReservasDAO(connection);
	}
	
	public void registraReservas(Reservas reservas) {
		this.reservasDao.registroReservas(reservas);
	}
	
	public List<Reservas> reservasRegistro(){
		return this.reservasDao.registroDasReservas();
	}
	
	public List<Reservas> buscarReserva(String busca){
		return this.reservasDao.pesquisarReservas(busca);
	}
	
	public void EditarReservas(LocalDate dataEntrada, LocalDate dataSaida, String valor, String formaPagamento, Integer id) {
	 this.reservasDao.editarDadosReservas(dataEntrada, dataSaida, valor, formaPagamento, id);
	}
	
	public void deletarReservas(Integer id) {
		this.reservasDao.excluirReservas(id);
	}
}
