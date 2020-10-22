package com.pdcase.crudpd.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pdcase.crudpd.model.Biblioteca;
import com.pdcase.crudpd.model.Pessoa;
import com.pdcase.crudpd.service.BibliotecaService;
import com.pdcase.crudpd.service.PessoaService;

// Named serve pra fazer com que seja um bean gerenciado. Nome padrão é pessoaController pra acesso nas views
@Named
// Escopo das variáveis do controlador.
@RequestScoped

public class BibliotecaController implements Serializable{
	private static final long serialVersionUID = 1L;

	// Camada de service
	@Inject
	private BibliotecaService bibliotecaService;
	
	
	//Logger pra erros
	private transient Logger log;

	// Modelo utilizado durante os requests
	@Inject
	private Biblioteca newLivro;

	public Biblioteca getNewLivro() {
		return newLivro;
	}

	public void setNewLivro(Biblioteca newLivro) {
		this.newLivro = newLivro;
	}

	@PostConstruct
	public void initNewLivro() {
		newLivro = new Biblioteca();
	}

	public List<Biblioteca> getAllLivros() {
		return bibliotecaService.getAllLivros();
	}

	// Salva o objeto salvo no request
	public void register() {
		try {
			bibliotecaService.register(newLivro);

			initNewLivro();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	// Apaga o objeto passado por id no request
	public void delete(int id) {
		try {
			bibliotecaService.delete(id);

			initNewLivro();
		} catch (Exception e) {

			log.info(e.getMessage());
		}
	}

	// Carrega o objeto passado por id para edição
	public void edit(int id) {
		try {
			newLivro = bibliotecaService.edit(id);

		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
}
