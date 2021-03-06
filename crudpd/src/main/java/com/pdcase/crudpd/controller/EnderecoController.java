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
	private EnderecoService enderecoService;
	
	
	//Logger pra erros
	@Inject
	private Logger log;

	// Modelo utilizado durante os requests
	@Inject
	private Endereco newEndereco;

	public Endereco getNewEndereco() {
		return newEndereco;
	}

	public void setNewEndereco(Endereco newEndereco) {
		this.newEndereco = newEndereco;
	}

	@PostConstruct
	public void initNewEndereco() {
		newEndereco = new Endereco();
	}

	public List<Endereco> getAllEndereco() {
		return enderecoService.getAllEndereco();
	}

	// Salva o objeto salvo no request
	public void register() {
		try {
			enderecoService.register(newEndereco);

			initNewEndereco();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	// Apaga o objeto passado por id no request
	public void delete(int id) {
		try {
			enderecoService.delete(id);

			initNewEndereco();
		} catch (Exception e) {

			log.info(e.getMessage());
		}
	}

	// Carrega o objeto passado por id para edição
	public void edit(int id) {
		try {
			newEndereco = enderecoService.edit(id);

		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

}