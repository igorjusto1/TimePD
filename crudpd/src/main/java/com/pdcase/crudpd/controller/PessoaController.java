package com.pdcase.crudpd.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pdcase.crudpd.model.Pessoa;
import com.pdcase.crudpd.service.PessoaService;

// Named serve pra fazer com que seja um bean gerenciado. Nome padrão é pessoaController pra acesso nas views
@Named
// Escopo das variáveis do controlador.
@RequestScoped
public class PessoaController implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	// Camada de service
	@Inject
	private PessoaService pessoaService;
	
	
	//Logger pra erros
	private transient Logger log;

	// Modelo utilizado durante os requests
	@Inject
	private Pessoa newPessoa;

	public Pessoa getNewPessoa() {
		return newPessoa;
	}

	public void setNewPessoa(Pessoa newPessoa) {
		this.newPessoa = newPessoa;
	}

	@PostConstruct
	public void initNewPessoa() {
		newPessoa = new Pessoa();
	}

	public List<Pessoa> getAllPessoas() {
		return pessoaService.getAllPessoas();
	}

	// Salva o objeto salvo no request
	public void register() {
		try {
			pessoaService.register(newPessoa);

			initNewPessoa();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	// Apaga o objeto passado por id no request
	public void delete(int id) {
		try {
			pessoaService.delete(id);

			initNewPessoa();
		} catch (Exception e) {

			log.info(e.getMessage());
		}
	}

	// Carrega o objeto passado por id para edição
	public void edit(int id) {
		try {
			newPessoa = pessoaService.edit(id);

		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

}
