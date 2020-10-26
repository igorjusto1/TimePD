package com.pdcase.crudpd.model;

<<<<<<< HEAD

import java.io.Serializable;
import java.util.List;
=======
import java.io.Serializable;
//import java.util.List;
>>>>>>> e6ee1909c9316b1e32a967bb29ccd98f9e9b6932

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

<<<<<<< HEAD

 @Entity
 @Table(name = "CADASTRO_TESTE")

 public class Cadastro  implements Serializable {
		private static final long serialVersionUID = 1L;
 @Id
=======
@Entity
@Table(name = "CADASTRO_TESTE")

public class Cadastro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
>>>>>>> e6ee1909c9316b1e32a967bb29ccd98f9e9b6932
	@SequenceGenerator(name = "CADASTRO_TESTE_SEQ", sequenceName = "CADASTRO_TESTE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CADASTRO_TESTE_SEQ")	
	@Column(name = "id")
	private int id;
<<<<<<< HEAD
 
     private String cidade;
     private String bairro;
     private int cep;
=======
	
	private String cidade;
	private String bairro;
	private int cep;
>>>>>>> e6ee1909c9316b1e32a967bb29ccd98f9e9b6932
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
<<<<<<< HEAD
	public List<Cadastro> getAllCadastro() {
		// TODO Auto-generated method stub
		return null;
	}
    

  
 }
 
  
=======
  
 }
>>>>>>> e6ee1909c9316b1e32a967bb29ccd98f9e9b6932
