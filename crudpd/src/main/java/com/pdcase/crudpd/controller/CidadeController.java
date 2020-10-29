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

import com.pdcase.crudpd.model.Estado;
import com.pdcase.crudpd.service.CidadeService;
import com.pdcase.crudpd.viewmodel.CidadeViewModel;
import com.pdcase.crudpd.viewmodel.EstadoSelectList;

@Named
@RequestScoped

public class CidadeController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CidadeService cidadeService;

	@Inject
	private Logger log;

	private CidadeViewModel newCidade;

	// Cidades na view
	private List<CidadeViewModel> cidades;

	// Cidades para cadastro
	private List<EstadoSelectList> estados;

	@Inject
	private FacesContext facesContext;

	public CidadeService getCidadeService() {
		return cidadeService;
	}

	public void setCidadeService(CidadeService cidadeService) {
		this.cidadeService = cidadeService;
	}

	/* Getters, setters */

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public CidadeViewModel getNewCidade() {
		return newCidade;
	}

	public void setNewCidade(CidadeViewModel newCidade) {
		this.newCidade = newCidade;
	}

	public List<CidadeViewModel> getCidades() {
		return cidades;
	}

	public void setCidades(List<CidadeViewModel> cidades) {
		this.cidades = cidades;
	}

	public List<EstadoSelectList> getEstados() {
		return estados;
	}

	public void setEstados(List<EstadoSelectList> estados) {
		this.estados = estados;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	/* Métodos */

	@PostConstruct
	public void init() {
		refreshList();
		cleanCidade();
	}

	public void refreshList() {
		cidades = cidadeService.getAllCidadesViewModel();
		estados = cidadeService.getAllEstados();
	}

	public void cleanCidade() {
		newCidade = new CidadeViewModel();
	}

	// Salva o objeto no request
	public void register() {
		try {
			cidadeService.register(newCidade);

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado!",
					"Registro de " + newCidade.getNomeCidade() + "completado com sucesso.");
			facesContext.addMessage(null, m);

			init();
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
			cidadeService.delete(id);

			init();
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
			newCidade = cidadeService.edit(id);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição carregada!",
					"Dados de " + newCidade.getNomeCidade() + " carregados para edição com sucesso");
			facesContext.addMessage(null, m);
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Edição não carregada");
			facesContext.addMessage(null, m);

			log.info(errorMessage);
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