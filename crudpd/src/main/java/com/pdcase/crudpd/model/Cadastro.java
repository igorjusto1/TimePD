package com.pdcase.crudpd.model;

import java.io.Serializable;
//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ENDERECO_TESTE")

public class Cadastro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CADASTRO_TESTE_SEQ", sequenceName = "CADASTRO_TESTE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CADASTRO_TESTE_SEQ")
	@Column(name = "id")
	private int id;

	private String cidade;
	private String bairro;
	private int cep;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString() {
		return this.cidade + ", " + this.bairro + ", " + this.cep;
	}

}
