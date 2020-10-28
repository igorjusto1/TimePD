package com.pdcase.crudpd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_estado") // FK -> estado_teste.id_estado
	private Estado estado;

	@Column(name = "nome_cidade", nullable = false)
	private String nomeCidade;

	public int getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(int id) {
		this.idCidade = id;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nome) {
		this.nomeCidade = nome;
	}

}
