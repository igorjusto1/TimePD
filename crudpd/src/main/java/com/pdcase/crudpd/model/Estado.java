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
@Table(name="estado_teste")
public class Estado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final String sequence = "ESTADO_TESTE_SEQ";
	
	@Id
	@SequenceGenerator(name = sequence, sequenceName = sequence, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequence)
	
	/* TODOS OS CAMPOS SÃO NOT NULL */

	@Column(name = "id_estado", nullable = false) // PK
	private int idEstado;

	@Column(name = "sigla_estado", nullable = false)
	private String siglaEstado;

	@Column(name = "nome_estado", nullable = false)
	private String nomeEstado;

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int id) {
		this.idEstado = id;
	}

	public String getSiglaEstado() {
		return siglaEstado;
	}

	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

}
