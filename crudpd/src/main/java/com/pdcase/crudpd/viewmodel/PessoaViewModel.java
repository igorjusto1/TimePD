package com.pdcase.crudpd.viewmodel;

import java.util.Date;

import com.pdcase.crudpd.model.Pessoa;

public class PessoaViewModel {

	public PessoaViewModel() {
		// Construtor padrão
	}

	public PessoaViewModel(Pessoa p) {
		this.id = p.getId();
		this.nome = p.getNome();
		this.sobrenome = p.getSobrenome();
		this.cpf = p.getCpf();
		this.nascimento = p.getNascimento();
		this.endereco = new CadastroSelectList(p.getEndereco());

	}

	private int id;

	private String nome;

	private String sobrenome;

	private String cpf;

	private Date nascimento;

	private CadastroSelectList endereco;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public CadastroSelectList getEndereco() {
		return endereco;
	}

	public void setEndereco(CadastroSelectList endereco) {
		this.endereco = endereco;
	}

	public Pessoa toPessoa() {
		Pessoa p = new Pessoa();
		p.setId(this.id);
		p.setNome(this.nome);
		p.setSobrenome(this.sobrenome);
		p.setNascimento(this.nascimento);
		p.setCpf(this.cpf);
		p.setEndereco(this.endereco.toEndereco());
		return p;
	}
}
