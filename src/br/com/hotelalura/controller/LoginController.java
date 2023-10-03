package br.com.hotelalura.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.hotelalura.modelo.UsuarioLogin;
import views.Login;
import views.MenuUsuario;

public class LoginController implements ActionListener {
	private Login view;
	
	public LoginController(Login view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String nome = view.getNome();
		String senha = view.getSenha();
		
		if(UsuarioLogin.validarLogin(nome, senha)) {
			MenuUsuario menu = new MenuUsuario();
			menu.setVisible(true);
			view.dispose();
		}else {
			JOptionPane.showMessageDialog(view, "Usuário ou senha incorretos");
		}
	}
}
