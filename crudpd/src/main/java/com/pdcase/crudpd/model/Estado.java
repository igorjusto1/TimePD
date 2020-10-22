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
@Table(name="tb_estado_teste")
public class Estado implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	@Id
	@SequenceGenerator(name = "ESTADO_TESTE_SEQ", sequenceName = "ESTADO_TESTE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADO_TESTE_SEQ")
	
	/* NOTA: TODOS OS CAMPOS SÃO NOT NULL */

	@Column(name = "id_estado") // PK
	private int idEstado;

	@Column(name = "sigla_estado")
	private String siglaEstado;

	@Column(name = "nome_estado")
	private String nomeEstado;

	public int getId_Estado() {
		return idEstado;
	}

	public void setId(int id) {
		this.idEstado = id;
	}

	public String getSigla_estado() {
		return siglaEstado;
	}

	public void setSigla_estado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

}
