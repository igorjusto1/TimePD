package com.pdcase.crudpd.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pdcase.crudpd.model.Cidade;
import com.pdcase.crudpd.service.CidadeService;

@Named
@RequestScoped
public class CidadeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CidadeService cidadeService;

	private transient Logger log;

	@Inject
	private Cidade newCidade;

	public Cidade getNewCidade() {
		return newCidade;
	}

	public void setNewCidade(Cidade newCidade) {
		this.newCidade = newCidade;
	}

	@PostConstruct
	public void initNewCidade() {
		newCidade = new Cidade();
	}

	public List<Cidade> getAllCidades() {
		return cidadeService.getAllCidades();
	}

	public void register() {
		try {
			cidadeService.register(newCidade);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	public void delete(int id) {
		try {
			cidadeService.delete(id);

			initNewCidade();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	public void edit(int id) {
		try {
			newCidade = cidadeService.edit(id);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

}