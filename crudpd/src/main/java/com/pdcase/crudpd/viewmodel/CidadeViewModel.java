package com.pdcase.crudpd.viewmodel;

import java.io.Serializable;

import com.pdcase.crudpd.model.Cidade;
import com.pdcase.crudpd.model.Estado;

public class CidadeViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String nomeCidade;
	private String nomeEstado;
	private String siglaEstado;
	private Estado estado;
	private EstadoSelectList listaEstados;

	public CidadeViewModel() {
		// Construtor standard
		// Vai inicializar o Estado
		this.estado = new Estado();
	}

	public CidadeViewModel(Cidade c) {
		this.id = c.getIdCidade();
		this.nomeCidade = c.getNomeCidade();
		this.estado = c.getEstado();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nome) {
		this.nomeCidade = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public String getNomeEstado() {
		return this.estado.getNomeEstado();
	}

	public EstadoSelectList getListaEstado() {
		return listaEstados;
	}
	
	public Cidade toCidade() {
		Cidade c = new Cidade();
		
		c.setIdCidade(id);
		c.setNomeCidade(nomeCidade);
		c.setEstado(estado);
		
		return c;
	}

	public Estado toEstado() {
		Estado e = new Estado();

		e.setIdEstado(id);
		e.setNomeEstado(nomeEstado);
		e.setSiglaEstado(siglaEstado);

		return e;
	}

}
