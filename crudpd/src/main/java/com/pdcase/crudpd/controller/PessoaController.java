package com.pdcase.crudpd.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.pdcase.crudpd.data.PessoaRepositorio;
import com.pdcase.crudpd.model.Pessoa;
import com.pdcase.crudpd.service.PessoaRegistration;

@Named
@RequestScoped
public class PessoaController implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private PessoaRegistration pessoaRegistration;

	@Inject
	private Pessoa newPessoa;
	
	@Inject
	private PessoaRepositorio pr;

	

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
	
	
	public List<Pessoa> getAllPessoas(){
		return pr.getListPessoas();
	}

	// Salva o objeto salvo no request
	public void register() throws Exception {
		try {
			pessoaRegistration.register(newPessoa);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewPessoa();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	// Apaga o objeto passado por id no request
	public void delete(int id) throws Exception {
		try {
			pessoaRegistration.delete(id);
			
			
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Apagado!", "Registro apagado");
			facesContext.addMessage(null, m);
			initNewPessoa();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registro não apagado");
			facesContext.addMessage(null, m);
		}
	}
	
	// Carrega o objeto passado por id para edição
	public void edit(int id) throws Exception {
		try {
			newPessoa = pessoaRegistration.edit(id);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Encontrado!", "Registro encontrado");
			facesContext.addMessage(null, m);
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registro não encontrado");
			facesContext.addMessage(null, m);
		}
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Registration failed. See server log for more information";
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
