package views;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.hotelalura.controller.HospedesController;
import br.com.hotelalura.controller.ReservasController;
import br.com.hotelalura.modelo.Hospedes;
import br.com.hotelalura.modelo.Reservas;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	private ReservasController reservaController;
	private HospedesController hospedeController;
	private ReservasView reservasView;
	String reserva;
	String hospede;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {
		this.hospedeController = new HospedesController();
		this.reservaController = new ReservasController();
		this.reservasView = new ReservasView();
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground( Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(75, 0, 130));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(75, 0, 130));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Número de Reserva");
		modelo.addColumn("Data Check-In");
		modelo.addColumn("Data Check-Out");
		modelo.addColumn("Valor hospedagem");
		modelo.addColumn("Forma de Pagar");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("RESERVAS", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		mostrarTabelaDeReservas();
		
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Número de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Documento");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Número de Reserva");
		mostrarTabelaDeHospedes();
		
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("HOSPEDES", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(75, 0, 130));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(new Color(75, 0, 130));
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(75, 0, 130));
		separator_1_2.setBackground(new Color(75, 0, 130));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpaTela();
				if(txtBuscar.getText().equals("")) {
					mostrarTabelaDeReservas();
					mostrarTabelaDeHospedes();
					
				}else {
					buscarReservas();
					buscarHospedes();
					
				} 
			}
			
		});
		
		
		
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(75, 0, 130));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int registroReservas = tbReservas.getSelectedRow();
				int filaHospedes = tbHospedes.getSelectedRow();
				if(registroReservas >= 0) {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja altarar esse(s) dado(s)?");
					if(confirmar == JOptionPane.YES_OPTION) {
						editarRegistroReservas();
						limpaTela();
						mostrarTabelaDeReservas();
						mostrarTabelaDeHospedes();
					}
					
				}else if(filaHospedes >= 0) {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja alterar esse(s) dado(s)?");
					if(confirmar == JOptionPane.YES_OPTION) {
						hospedesEditarDados();
						limpaTela();
						mostrarTabelaDeHospedes();
						mostrarTabelaDeReservas();
					} 
					
				}
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(75, 0, 130));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnRegistro = new JPanel();
		btnRegistro.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mostrarTabelaDeHospedes();
				mostrarTabelaDeReservas();
			}
		});
			
		
	
		btnRegistro.setLayout(null);
		btnRegistro.setBackground(new Color(75, 0, 130));
		btnRegistro.setBounds(503, 508, 122, 35);
		btnRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnRegistro);
		
		JLabel lblRegistro = new JLabel("REGISTRO");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setForeground(Color.WHITE);
		lblRegistro.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblRegistro.setBounds(0, 0, 122, 35);
		btnRegistro.add(lblRegistro);
		
		
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int registroReservas = tbReservas.getSelectedRow();
				int filaHospedes = tbHospedes.getSelectedRow();
				
				if(registroReservas >= 0) {
					reserva = tbReservas.getValueAt(registroReservas, 0).toString();
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja excluir a reserva?");
					if(confirmar == JOptionPane.YES_OPTION) {
						String valor = tbReservas.getValueAt(registroReservas, 0).toString();
						reservaController.deletarReservas(Integer.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Reserva excluída com sucesso!");
						limpaTela();
						mostrarTabelaDeReservas();
						mostrarTabelaDeHospedes();
					}
				} 
				if(filaHospedes >= 0){
					hospede = tbHospedes.getValueAt(filaHospedes,0).toString();
					int confirma = JOptionPane.showConfirmDialog(null,"Deseja excluir esse(s) dado(s)?");
					if(confirma == JOptionPane.YES_OPTION) {
						String hospede = tbHospedes.getValueAt(filaHospedes, 0).toString();
						hospedeController.excluirHospede(Integer.valueOf(hospede));
						JOptionPane.showMessageDialog(contentPane, "Dado(s) excluído(s) com sucesso!");
						limpaTela();
						mostrarTabelaDeHospedes();
						mostrarTabelaDeReservas();
						
					}
				}
			}
		});
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(75, 0, 130));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}
	//code de mostrar,buscar,editar e excluir reservas
	private List<Reservas> registroReserva() {
		return this.reservaController.reservasRegistro();
	}
	
	private List<Reservas> reservasBuscar() {
		return this.reservaController.buscarReserva(txtBuscar.getText());
	}
	
	public void mostrarTabelaDeReservas() {
		List<Reservas> reservas = registroReserva();
		modelo.setRowCount(0);
		try {
			for(Reservas reserva : reservas) {
				modelo.addRow(new Object[]{
					reserva.getId(), reserva.getDataEntrada(), reserva.getDataSaida(), reserva.getValor(), reserva.getFormaPagamento()
				});
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void editarRegistroReservas() {

		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresent(fila ->{
			LocalDate dataEntrada;
			LocalDate dataSaida;
			
			try {
				DateTimeFormatter dFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				dataEntrada = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString(), dFormat);
				dataSaida = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString(), dFormat);
			}catch(DateTimeException e) {
				throw new RuntimeException(e);
			}
			this.reservasView.limparValorReservas();
			
			String valor = reservaCalcularValor(dataEntrada, dataSaida);
			String formaPagamento = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
			Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
			
			if(tbReservas.getSelectedColumn() == 0) {
				JOptionPane.showMessageDialog(this,"O ID não pode ser alterado");
			}else {
				this.reservaController.EditarReservas(dataEntrada, dataSaida, valor, formaPagamento, id);
				JOptionPane.showMessageDialog(this, String.format("Alteração feita com sucesso"));
			}
		});
	}
	
	public String reservaCalcularValor(LocalDate dataEntrada, LocalDate dataSaida) {
		if(dataEntrada != null && dataSaida != null) {
			int dias = (int) ChronoUnit.DAYS.between(dataEntrada, dataSaida);
			int diaria = 77;
			int valor = dias * diaria;
			
			return Integer.toString(valor);
			
		}else {
			return "";
		}
	}
	
	public void buscarReservas() {
		List<Reservas> reservas = reservasBuscar();
		modelo.setRowCount(0);
		try {
			for(Reservas reserva : reservas) {
				modelo.addRow(new Object[]{
					reserva.getId(), reserva.getDataEntrada(), reserva.getDataSaida(), reserva.getValor(), reserva.getFormaPagamento()
				});
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	//code de mostrar,buscar,editar e excluir dados dos hospedes
	
	private List<Hospedes> dadosHospedes(){
		return this.hospedeController.registroHospedes();
	}
	private List<Hospedes> pesquisarDadosHospedes(){
		return this.hospedeController.hospedesBuscar(txtBuscar.getText());
	}
	
	
	
	public void mostrarTabelaDeHospedes() {
		List<Hospedes> hospedes = dadosHospedes();
		modeloHospedes.setRowCount(0);
		try {
			for(Hospedes hospede: hospedes) {
				modeloHospedes.addRow(new Object[]{
					hospede.getId(), hospede.getNome(), hospede.getSobrenome(),hospede.getDocumentos(), hospede.getDataNascimento(), hospede.getNacionalidade(),
					hospede.getTelefone(), hospede.getReservaId()
				});
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void buscarHospedes() {

		List<Hospedes> hospedes = pesquisarDadosHospedes();
		modeloHospedes.setRowCount(0);
		try {
			for(Hospedes hospede: hospedes) {
				modeloHospedes.addRow(new Object[]{
					hospede.getId(), hospede.getNome(), hospede.getSobrenome(),hospede.getDocumentos(), hospede.getDataNascimento(), hospede.getNacionalidade(),
					hospede.getTelefone(), hospede.getReservaId()
				});
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void hospedesEditarDados() {
		Optional.ofNullable(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn()))
		.ifPresentOrElse(filaHospede ->{
			String nome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1);
			String sobrenome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2);
			String documentos = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 3);
			LocalDate dataNascimento = (LocalDate) LocalDate.parse(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4).toString());
			String nacionalidade = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5);
			String telefone = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 6);
			Integer reservaId = Integer.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(),7).toString());
			
			Integer id = Integer.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(),0).toString()); 
			
			
			if(tbHospedes.getSelectedColumn() == 0 || tbHospedes.getSelectedColumn() == 7){
				JOptionPane.showMessageDialog(contentPane,"O ID não pode ser alterado");
			}else {
				this.hospedeController.editarDadosHospedes(nome, sobrenome,documentos,dataNascimento, nacionalidade, telefone,reservaId,id);
				JOptionPane.showMessageDialog(this,String.format("Dado(s) atualizado(s) com sucesso!"));
				
			}			
			
		}, null);
	}
	
	
	private void limpaTela() {
		((DefaultTableModel) tbHospedes.getModel()).setRowCount(0);
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
	}
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
