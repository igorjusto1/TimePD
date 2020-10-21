package com.pdcase.crudpd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "biblioteca_teste")

public class Biblioteca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "BIBLIOTECA_TESTE_SEQ", sequenceName = "BIBLIOTECA_TESTE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BIBLIOTECA_TESTE_SEQ")	
	@Column(name = "id")
	private int id;

	@Column(name = "nome_livro")
	private String nome_livro;

	@Column(name = "data_publicacao")
	private Date data_publicacao;

	@Column(name = "editora")
	private String editora;
	
	@Column(name = "paginas")
	private String paginas;
	
	@Column(name = "nome_autor")
	private String nome_autor;
	
	@Column(name = "descricao")
	private String descricao;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome_autor() {
		return nome_autor;
	}
	
	public void setNome_autor(String nome_autor) {
		this.nome_autor = nome_autor;
	}
	
	public String getNome_livro() {
		return nome_livro;
	}
	
	public void setNome_livro(String nome_livro) {
		this.nome_livro = nome_livro;
	}
	
	public Date getData_publicacao() {
		return data_publicacao;
	}
	
	public void setData_publicacao(Date data_publicacao) {
		this.data_publicacao = data_publicacao;
	}
	
	public String getEditora() {
		return editora;
	}
	
	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	public String getPaginas() {
		return paginas;
	}
	
	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
