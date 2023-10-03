package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import br.com.hotelalura.controller.HospedesController;
import br.com.hotelalura.controller.ReservasController;
import br.com.hotelalura.modelo.Hospedes;

@SuppressWarnings("serial")
public class RegistroHospede extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtDocumento;
	private JTextField txtTelefone;
	private JTextField txtNreserva;
	private JDateChooser txtDataN;
	private JComboBox<String> txtNacionalidade;
	private JLabel labelExit;
	private JLabel labelAtras;
	int xMouse, yMouse;
	private HospedesController hospedeController;
	@SuppressWarnings("unused")
	private ReservasController reservasController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroHospede frame = new RegistroHospede(0);
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
	public RegistroHospede(Integer reservaId) {
		
	
		this.hospedeController = new HospedesController();
		this.reservasController = new ReservasController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHospede.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBounds(-54, 0, 910, 36);
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
		
		JPanel btnexit = new JPanel();
		btnexit.setBounds(857, 0, 53, 36);
		contentPane.add(btnexit);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(new Color(75, 0, 130));
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.white);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(SystemColor.black);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservasView reservas = new ReservasView();
				reservas.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(87, 0, 157));
				labelAtras.setForeground(Color.black);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(new Color(75, 0, 130));
			     labelAtras.setForeground(Color.white);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(75, 0, 130));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(Color.WHITE);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNome.setBounds(560, 104, 285, 26);
		txtNome.setBackground(Color.WHITE);
		txtNome.setColumns(10);
		txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNome);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtSobrenome.setBounds(560, 164, 285, 26);
		txtSobrenome.setColumns(10);
		txtSobrenome.setBackground(Color.WHITE);
		txtSobrenome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtSobrenome);
		
		txtDocumento = new JTextField();
		txtDocumento.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtDocumento.setBounds(560, 220, 285, 21);
		txtDocumento.setBackground(Color.WHITE);
		txtDocumento.setColumns(10);
		txtDocumento.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtDocumento);
		
		txtDataN = new JDateChooser();
		txtDataN.setBounds(560, 280, 285, 26);
		txtDataN.getCalendarButton().setIcon(new ImageIcon(RegistroHospede.class.getResource("/imagenes/icon-reservas.png")));
		txtDataN.getCalendarButton().setBackground(new Color(75, 0, 130));
		txtDataN.setDateFormatString("yyyy-MM-dd");
		contentPane.add(txtDataN);
		
		txtNacionalidade = new JComboBox<String>();
		txtNacionalidade.setBounds(560, 350, 289, 37);
		txtNacionalidade.setBackground(SystemColor.text);
		txtNacionalidade.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNacionalidade.setModel(new DefaultComboBoxModel<String>(new String[] {"alemão", "andorrano", "angolano", "antiguano", "saudita", "argelino", "argentino", "armênio", "australiano", "austríaco", "azerbaijano", "bahamense", "bangladês, bangladense", "barbadiano", "bahreinita", "belga", "belizenho", "beninês", "belarusso", "boliviano", "bósnio", "botsuanês", "brasileiro", "bruneíno", "búlgaro", "burkineonse, burkinabé", "burundês", "butanês", "cabo-verdiano", "camerounês", "cambojano", "catariano", "canadense", "cazaque", "chadiano", "chileno", "chinês", "cipriota", "colombiano", "comoriano", "congolês", "congolês", "sul-coreano", "norte-coreano", "costa-marfinense, marfinense", "costa-ricense", "croata", "cubano", "dinamarquês", "djiboutiano", "dominiquense", "egípcio", "salvadorenho", "emiradense, emirático", "equatoriano", "eritreu", "eslovaco", "esloveno", "espanhol", "estadunidense, (norte-)americano", "estoniano", "etíope", "fijiano", "filipino", "finlandês", "francês", "gabonês", "gambiano", "ganês ou ganense", "georgiano", "granadino", "grego", "guatemalteco", "guianês", "guineense", "guineense, bissau-guineense", "equato-guineense", "haitiano", "hondurenho", "húngaro", "iemenita", "cookiano", "marshallês", "salomonense", "indiano", "indonésio", "iraniano", "iraquiano", "irlandês", "islandês", "34", "jamaicano", "japonês", "jordaniano", "kiribatiano", "kuwaitiano", "laosiano", "lesotiano", "letão", "libanês", "liberiano", "líbio", "liechtensteiniano", "lituano", "luxemburguês", "macedônio", "madagascarense", "malásio37", "malawiano", "maldivo", "maliano", "maltês", "marroquino", "mauriciano", "mauritano", "mexicano", "myanmarense", "micronésio", "moçambicano", "moldovo", "monegasco", "mongol", "montenegrino", "namibiano", "nauruano", "nepalês", "nicaraguense", "nigerino", "nigeriano", "niuiano", "norueguês", "neozelandês", "omani", "neerlandês", "palauano", "palestino", "panamenho", "papua, papuásio", "paquistanês", "paraguaio", "peruano", "polonês, polaco", "português", "queniano", "quirguiz", "britânico", "centro-africano", "tcheco", "dominicano", "romeno", "ruandês", "russo", "samoano", "santa-lucense", "são-cristovense", "samarinês", "santomense", "são-vicentino", "seichelense", "senegalês", "sérvio", "singapurense", "sírio", "somaliano, somali", "sri-lankês", "suázi", "sudanês", "sul-sudanês", "sueco", "suíço", "surinamês", "tajique", "tailandês", "tanzaniano", "timorense", "togolês", "tonganês", "trinitário", "tunisiano", "turcomeno", "turco", "tuvaluano", "ucraniano", "ugandês", "uruguaio", "uzbeque", "vanuatuense", "vaticano", "venezuelano", "vietnamita", "zambiano", "zimbabueano"}));
		contentPane.add(txtNacionalidade);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(562, 85, 253, 14);
		lblNome.setForeground(new Color(87, 0, 157));
		lblNome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNome);
		
		JLabel lblSobrenome = new JLabel("SOBRENOME");
		lblSobrenome.setBounds(560, 147, 255, 14);
		lblSobrenome.setForeground(new Color(87, 0, 157));
		lblSobrenome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblSobrenome);
		
		JLabel lblDocumentos = new JLabel("DOCUMENTO");
		lblDocumentos.setBounds(560, 198, 255, 14);
		lblDocumentos.setForeground(new Color(87, 0, 157));
		lblDocumentos.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblDocumentos);
		
		JLabel lblDataN = new JLabel("DATA DE NASCIMENTO");
		lblDataN.setBounds(560, 254, 255, 14);
		lblDataN.setForeground(new Color(87, 0, 157));
		lblDataN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblDataN);
		
		JLabel lblNacionalidade = new JLabel("NACIONALIDADE");
		lblNacionalidade.setBounds(560, 326, 255, 14);
		lblNacionalidade.setForeground(new Color(87, 0, 157));
		lblNacionalidade.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNacionalidade);
		
		JLabel lblTelefone = new JLabel("TELEFONE");
		lblTelefone.setBounds(562, 405, 253, 14);
		lblTelefone.setForeground(new Color(87, 0, 157));
		lblTelefone.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefone.setBounds(560, 429, 285, 27);
		txtTelefone.setColumns(10);
		txtTelefone.setBackground(Color.WHITE);
		txtTelefone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefone);
		
		JLabel lblTitulo = new JLabel("REGISTRO HÓSPEDE");
		lblTitulo.setBounds(579, 33, 267, 42);
		lblTitulo.setForeground(new Color(75, 0, 130));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		contentPane.add(lblTitulo);
		
		JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
		lblNumeroReserva.setBounds(560, 474, 253, 14);
		lblNumeroReserva.setForeground(new Color(87, 0, 157));
		lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNumeroReserva);
		
		txtNreserva = new JTextField();
		txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNreserva.setBounds(560, 507, 253, 14);
		txtNreserva.setColumns(10);
		txtNreserva.setBackground(Color.WHITE);
		txtNreserva.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		String id = String.valueOf(reservaId);
		txtNreserva.setText(id);
		contentPane.add(txtNreserva);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(556, 135, 289, 2);
		separator_1_2.setForeground(new Color(75, 0, 130));
		separator_1_2.setBackground(new Color(75, 0, 130));
		contentPane.add(separator_1_2);
		
		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 190, 289, 2);
		separator_1_2_1.setForeground(new Color(75, 0, 130));
		separator_1_2_1.setBackground(new Color(75, 0, 130));
		contentPane.add(separator_1_2_1);
		
		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 314, 289, 2);
		separator_1_2_2.setForeground(new Color(75, 0, 130));
		separator_1_2_2.setBackground(new Color(75, 0, 130));
		contentPane.add(separator_1_2_2);
		
		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 394, 289, 2);
		separator_1_2_3.setForeground(new Color(75, 0, 130));
		separator_1_2_3.setBackground(new Color(75, 0, 130));
		contentPane.add(separator_1_2_3);
		
		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 457, 289, 2);
		separator_1_2_4.setForeground(new Color(75, 0, 130));
		separator_1_2_4.setBackground(new Color(75, 0, 130));
		contentPane.add(separator_1_2_4);
		
		JSeparator separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 529, 289, 2);
		separator_1_2_5.setForeground(new Color(75, 0, 130));
		separator_1_2_5.setBackground(new Color(75, 0, 130));
		contentPane.add(separator_1_2_5);
		
		JSeparator separator_1_2_6 = new JSeparator();
		separator_1_2_6.setBounds(560, 244, 289, 2);
		separator_1_2_6.setForeground(new Color(75, 0, 130));
		separator_1_2_6.setBackground(new Color(75, 0, 130));
		contentPane.add(separator_1_2_6);
		
		JPanel btnsalvar = new JPanel();
		btnsalvar.setBounds(723, 560, 122, 35);
		btnsalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				salvaHospedes();
			}

			
		});
		btnsalvar.setLayout(null);
		btnsalvar.setBackground(new Color(75, 0, 130));
		contentPane.add(btnsalvar);
		btnsalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel labelSalvar = new JLabel("SALVAR");
		labelSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		labelSalvar.setForeground(Color.WHITE);
		labelSalvar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelSalvar.setBounds(0, 0, 122, 35);
		btnsalvar.add(labelSalvar);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(75, 0, 130));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imageFundo = new JLabel("");
		imageFundo.setBounds(0, 121, 479, 502);
		panel.add(imageFundo);
		imageFundo.setIcon(new ImageIcon(RegistroHospede.class.getResource("/imagenes/registro.png")));
		
		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(RegistroHospede.class.getResource("/imagenes/Ha-100px.png")));
	}
	
	private void salvaHospedes(){
    	
		if (txtDataN.getDate() != null && !txtNome.getText().equals("") && !txtSobrenome.getText().equals("") && !txtDocumento.getText().equals("") && !txtTelefone.getText().equals("")) {
			LocalDate dataN = LocalDate.parse(((JTextField)txtDataN.getDateEditor().getUiComponent()).getText());
			
			Integer reservaId = Integer.parseInt(txtNreserva.getText());
			
			
			
			Hospedes hospede = new Hospedes(txtNome.getText(), txtSobrenome.getText(),txtDocumento.getText(), dataN, txtNacionalidade.getSelectedItem().toString(), txtTelefone.getText(), reservaId );
			
			this.hospedeController.registrarHospedes(hospede);
			
			Sucesso sucesso = new Sucesso();
			sucesso.setVisible(true);
			dispose();
			
			
		} else {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
		}
		
	}
	
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" y "y"
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
