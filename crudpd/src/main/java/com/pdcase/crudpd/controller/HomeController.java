package com.pdcase.crudpd.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class HomeController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pessoaIndex = "/pessoa/index";

	public String pessoaIndex() {		
		return pessoaIndex;
	}

	private String bibliotecaIndex = "/biblioteca/index";

	public String bibliotecaIndex() {
		return bibliotecaIndex;
	}
	
	private String estadoIndex = "/estado/index";
	
	public String estadoIndex() {
		return estadoIndex;
	}
	
	private String enderecoIndex = "/endereco/index";
	
	public String cadastroIndex() {
		return enderecoIndex;
	}
	
	private String cidadeIndex = "/cidade/index";
	
	public String cidadeIndex() {
		return cidadeIndex;
	}
}
