package com.pdcase.crudpd.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	
	@Inject
	private PessoaService pessoaService;
	
	
	//Logger pra erros
	@Inject
	private Logger log;

	// Modelo utilizado durante os requests
	@Inject
	private Biblioteca newLivro;
	
	// Lista de pessoas na view
	private List<Biblioteca> livros;
	
	private List<Pessoa> autor;
	
	@Inject
	private FacesContext facesContext;
	

	public Biblioteca getNewLivro() {
		return newLivro;
	}

	public void setNewLivro(Biblioteca newLivro) {
		this.newLivro = newLivro;
	}
	
	public List<Biblioteca> getLivros() {
		return livros;
	}
	
	public void setLivros(List<Biblioteca> livros) {
		this.livros = livros;
	}
	
	public List<Pessoa> getAutor() {
		return autor;
	}
	
	public void setAutor(List<Pessoa> autor) {
		this.autor = autor;
	}

	@PostConstruct
	public void initNewLivro() {
		livros=bibliotecaService.getAllLivros();
		newLivro = new Biblioteca();
		autor=pessoaService.getAllPessoas();
		newLivro.setAutor(new Pessoa());
	}

	// Salva o objeto salvo no request
	
	public void register() {
		try {
			
			bibliotecaService.register(newLivro);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo!",
					"Registro >>> " + newLivro.getNome_livro() + " <<< salvo com sucesso");
			facesContext.addMessage(null, m);

			initNewLivro();

		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
			log.info(errorMessage);
		}
	}

	// Apaga o objeto passado por id no request
	
	public void delete(int id) {
		try {
			bibliotecaService.delete(id);
			initNewLivro();
			
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Remoção não concluída");
			facesContext.addMessage(null, m);

			log.info(errorMessage);
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
		
	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Operação falhou. Veja o log do servidor para mais informações";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}
}
