package com.pdcase.crudpd.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pdcase.crudpd.model.Endereco;
import com.pdcase.crudpd.service.EnderecoService;

// Named serve pra fazer com que seja um bean gerenciado. Nome padrão é pessoaController pra acesso nas views
@Named
// Escopo das variáveis do controlador.
@RequestScoped
public class EnderecoController implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	// Camada de service
	@Inject
	private EnderecoService cadastroService;
	
	
	//Logger pra erros
	private transient Logger log;

	// Modelo utilizado durante os requests
	@Inject
	private Endereco newCadastro;

	public Endereco getNewCadastro() {
		return newCadastro;
	}

	public void setNewCadasrtro(Endereco newCadastro) {
		this.newCadastro = newCadastro;
	}

	@PostConstruct
	public void initNewCadastro() {
		newCadastro = new Endereco();
	}

	public List<Endereco> getAllCadastro() {
		return cadastroService.getAllCadastro();
	}

	// Salva o objeto salvo no request
	public void register() {
		try {
			cadastroService.register(newCadastro);

			initNewCadastro();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	// Apaga o objeto passado por id no request
	public void delete(int id) {
		try {
			cadastroService.delete(id);

			initNewCadastro();
		} catch (Exception e) {

			log.info(e.getMessage());
		}
	}

	// Carrega o objeto passado por id para edição
	public void edit(int id) {
		try {
			newCadastro = cadastroService.edit(id);

		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

}