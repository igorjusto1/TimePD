package com.pdcase.crudpd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cidade_teste")
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String sequence = "CIDADE_TESTE_SEQ";

	@Id
	@SequenceGenerator(name = sequence, sequenceName = sequence, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequence)

	/* TODOS OS CAMPOS SÃO NOT NULL */

	@Column(name = "id_cidade", nullable = false) // PK
	private int idCidade;

	@Column(name = "id_estado", nullable = false) // FK -> estado_teste.id_estado
	private int idEstado;

	@Column(name = "nome_cidade", nullable = false)
	private String nomeCidade;

	public int getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

}
