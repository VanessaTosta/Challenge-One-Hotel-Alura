package br.com.hotelalura.modelo;


import java.time.LocalDate;

public class Hospedes {
	private Integer id;
	private String nome;
	private String sobrenome;
	private String documentos;
	private LocalDate dataNascimento;
	private String nacionalidade;
	private String telefone;
	private Integer reservaId;
	
	public Hospedes( String nome, String sobrenome,String documentos, LocalDate dataNascimento, String nacionalidade, String telefone, Integer reservaId) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documentos = documentos;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.reservaId = reservaId;
		this.documentos = documentos;
	}
	
	
	public Hospedes(int id, String nome, String sobrenome,String documentos, LocalDate dataNascimento, String nacionalidade,
			String telefone, int reservaId) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documentos = documentos;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.reservaId = reservaId;
		
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}
	
	

	public String getDocumentos() {
		return documentos;
	}


	public void setDocumentos(String documentos) {
		this.documentos = documentos;
	}


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getNacionalidade() {
		return nacionalidade;
	}
	

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public Integer getReservaId() {
		return reservaId;
	}

	public void setReservaId(Integer reservaId) {
		this.reservaId = reservaId;
	}


}
