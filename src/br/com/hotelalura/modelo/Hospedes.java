package br.com.hotelalura.modelo;

import java.sql.Date;

public class Hospedes {
	private Integer id;
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	protected String nacionalidade;
	private String telefone;
	private Integer reservaId;
	
	public Hospedes(Integer id, String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone, Integer reservaId) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
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

	public Date getDatanascimento() {
		return dataNascimento;
	}

	public String getNacionalidade() {
		return nacionalidade;
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
