package com.pdcase.crudpd.viewmodel;

import com.pdcase.crudpd.model.Cidade;

public class CidadeViewModel {

	private int idCidade;
	private String nome;
	private int idEstado;

	public CidadeViewModel() {

	}

	public CidadeViewModel(Cidade c) {
		this.idCidade = c.getIdCidade();
		this.nome = c.getNomeCidade();
		this.idEstado = c.getIdEstado();
	}

	public int getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public Cidade toCidade() {
		Cidade c = new Cidade();

		c.setIdCidade(this.idCidade);
		c.setIdEstado(this.idEstado);
		c.setNomeCidade(this.nome);

		return c;
	}

}
