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

	

	private String pessoaIndex = "pessoa/index?faces-redirect = true";
	
	public String pessoaIndex() {
		return pessoaIndex;
	}

}
