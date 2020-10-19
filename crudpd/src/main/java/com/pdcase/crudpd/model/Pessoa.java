package com.pdcase.crudpd.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "pessoa_teste")
public class Pessoa {
	  @Id
	    @GeneratedValue
	    private Long id;
	  
	  private String nome;
	  
	  private String sobrenome;
	  
	  private String cpf;
	  
	  private Date nascimento;
}
